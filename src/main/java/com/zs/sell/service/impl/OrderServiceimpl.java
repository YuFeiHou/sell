package com.zs.sell.service.impl;

import com.zs.sell.converter.OrderMaster0rderDtoConverter;
import com.zs.sell.dataobject.OrderDetail;
import com.zs.sell.dataobject.OrderMaster;
import com.zs.sell.dataobject.ProductInfo;
import com.zs.sell.dto.CartDto;
import com.zs.sell.dto.OrderDto;
import com.zs.sell.enums.OrderAmountEnum;
import com.zs.sell.enums.PayStatusEnum;
import com.zs.sell.enums.ResultEnum;
import com.zs.sell.exception.SellException;
import com.zs.sell.repository.OrderDetailRepository;
import com.zs.sell.repository.OrderMasterRepository;
import com.zs.sell.service.OrderService;
import com.zs.sell.service.ProductService;
import com.zs.sell.service.PushMessageService;
import com.zs.sell.service.WebSocket;
import com.zs.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.annotation.WebServlet;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fei
 * 日期: 2018-11-03
 * 时间: 9:52
 */
@Service
@Slf4j
public class OrderServiceimpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private WebSocket webSocket;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {

        //默认价格为0.00
        BigDecimal bigDecimal = new BigDecimal(BigInteger.ZERO);

        //用户收购买的商品列表（这里为了实现一次性扣库存）
        //List<CartDto> cartDtoList = new ArrayList<>();

        //在创建订单的时候生成订单主表id
        String orderMasterId = KeyUtil.genUniquekey();

        //1.查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            //判断商品是否存在，如果不存在就抛出一个异常
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //3.计算订单总价（商品单价*（multiply）商品数量+（add）基础价格）
            bigDecimal = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(bigDecimal);
            //4.订单详情入库（用的是数据库的数据，避免前端更改）
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setOrderId(orderMasterId);
            orderDetail.setDetailId(KeyUtil.genUniquekey());
            //保存订单详情表入库
            orderDetailRepository.save(orderDetail);

            //减库存(根据当前程序去扣库存，可以使用一次性扣多个商品库存的方法)
//            CartDto cartDto = new CartDto(orderDetail.getProductId(),orderDetail.getProductQuantity());
//            cartDtoList.add(cartDto);
        }
        //5.写入数据库 先拷贝在设置，不然orderDto里面的空数据会覆盖之前设置进去的值
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderMasterId);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(bigDecimal);
        orderMaster.setOrderStatus(OrderAmountEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        //保存订单主表入库
        orderMasterRepository.save(orderMaster);
        //4.减库存（一下减掉多个商品的库存）
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDtoList);

        //5.消息推送
        webSocket.sendMessage("有新的订单消息，注意查收！");
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {

        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NIT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (orderDetailList == null) {
            throw new SellException(ResultEnum.ORDERDETALI_NOT_EXIST);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> pageOrderMaster = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        //首先获取到OrderMaster列表，然后进行转换
        List<OrderDto> orderDtoList = OrderMaster0rderDtoConverter.convert(pageOrderMaster.getContent());
        return new PageImpl<OrderDto>(orderDtoList, pageable, pageOrderMaster.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        //转换
        OrderMaster orderMaster = new OrderMaster();
        //1.判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderAmountEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确！ orderId={}，orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改订单状态
        orderDto.setOrderStatus(OrderAmountEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【取消订单】更新失败！ orderId={}，orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //3.返还库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【取消订单】不存在商品！orderDto={}", orderDto);
            throw new SellException(ResultEnum.ORDRR_DETAIL_EMPTY);
        }
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.increaseStock(cartDtoList);
        //4.如果已经支付，需要退款
        if (orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO
        }
        return orderDto;
    }

    @Override
    public OrderDto finsh(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        //1.判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderAmountEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确！ orderId={}，orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改订单状态
        orderDto.setOrderStatus(OrderAmountEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败！ orderId={}，orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //3.消息推送
        pushMessageService.orderStatus(orderDto);
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto paid(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        //1.判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderAmountEnum.NEW.getCode())) {
            log.error("【支付的订单状态】订单状态不正确！ orderId={}，orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.判断支付状态
        if (!orderDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【支付的订单状态】订单支付状态不正确！orderDto={}", orderDto);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //3.修改支付状态,订单状态
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        orderDto.setOrderStatus(OrderAmountEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【支付的订单状态】更新失败！ orderDto={}", orderDto);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(Pageable pageable) {
        Page<OrderMaster> pageOrderMaster = orderMasterRepository.findAll(pageable);
        //首先获取到OrderMaster列表，然后进行转换
        List<OrderDto> orderDtoList = OrderMaster0rderDtoConverter.convert(pageOrderMaster.getContent());
        return new PageImpl<OrderDto>(orderDtoList, pageable, pageOrderMaster.getTotalElements());
    }
}
