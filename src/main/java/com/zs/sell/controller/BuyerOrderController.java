package com.zs.sell.controller;

import com.zs.sell.converter.OrderFrom2OrderDTOConverter;
import com.zs.sell.dto.OrderDto;
import com.zs.sell.enums.ResultEnum;
import com.zs.sell.exception.SellException;
import com.zs.sell.from.OrderFrom;
import com.zs.sell.service.BuyerService;
import com.zs.sell.service.OrderService;
import com.zs.sell.utils.ResultUtil;
import com.zs.sell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fei
 * @title: BuyerOrderController
 * @projectName sell
 * @description: TODO
 * @date 2019/5/17 14:44
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderFrom orderFrom,
                                                BindingResult bindingResult) {
        //先校验，看传入参数是否正确
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】 参数不正确，orderFrom={}", orderFrom);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderFrom2OrderDTOConverter.convert(orderFrom);
        //集合判断为空的方法
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【创建订单】 订单中不能没有商品，orderFrom={}", orderFrom);
            throw new SellException(ResultEnum.CART_ERROR);
        }
        //创建订单
        OrderDto createResult = orderService.create(orderDto);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultUtil.Success(map);
    }

    //订单列表
    @GetMapping("/orderList")
    public ResultVo<List<OrderDto>> orderList(@RequestParam("openid") String openid,
                                              @RequestParam(value = "page", defaultValue = "0") Integer page,
                                              @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page, size);
        Page<OrderDto> orderDtoPage = orderService.findList(openid, request);

        return ResultUtil.Success(orderDtoPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVo<List<OrderDto>> detail(@RequestParam("openid") String openid,
                                           @RequestParam("orderId") String orderId) {
        //这样做没有权限的验证
        OrderDto orderDto = buyerService.findOrderOne(openid, orderId);
        return ResultUtil.Success(orderDto);
    }

    //取消订单
    @GetMapping("/cancel")
    public ResultVo<List<OrderDto>> cancel(@RequestParam("openid") String openid,
                                           @RequestParam("orderId") String orderId) {
        //这样做没有权限的验证(凡是涉及到业务逻辑的判断都需要在业务层判断)
        OrderDto orderDto = buyerService.cancelOrder(openid, orderId);
        return ResultUtil.Success();
    }
}
