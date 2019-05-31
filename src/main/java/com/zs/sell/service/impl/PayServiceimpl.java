package com.zs.sell.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.zs.sell.dto.OrderDto;
import com.zs.sell.enums.ResultEnum;
import com.zs.sell.exception.SellException;
import com.zs.sell.service.OrderService;
import com.zs.sell.service.PayService;
import com.zs.sell.utils.JsonUtil;
import com.zs.sell.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author fei
 * @title: PayServiceimpl
 * @projectName sell
 * @description: TODO
 * @date 2019/5/18 19:34
 */
@Service
@Slf4j
public class PayServiceimpl implements PayService {

    private static final String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayService bestPayService;
    @Autowired OrderService orderService;

    @Override
    public PayResponse create(OrderDto orderDto) {
        //1.支付配置
        //2.调用支付方法
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDto.getBuyerOpenid());
        payRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】payRequest={}",payRequest);
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】payResponse={}",payResponse);
        return payResponse;
    }


    /**
     * 1.验证签名
     * 2.微信支付状态
     * 前两个在bestPayService里面已经做出了相应的配置
     * 支付金额（预处理订单校验金额）
     * 4.支付人（下单人---->支付人）
     * @param notifyData
     * @return
     */
    @Override
    public PayResponse notify(String notifyData) {
        //处理异步通知
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知信息 payResponse={}", JsonUtil.toJson(notifyData));
        //查询订单
       OrderDto orderDto = orderService.findOne(payResponse.getOrderId());

       //判断订单是否存在
        if(orderDto == null){
            log.error("【微信支付】异步通知，订单不存在 orderId={]",orderDto.getOrderId());
            throw new SellException(ResultEnum.ORDER_NIT_EXIST);
        }

        //金额校验（不要拿double与double互相比较，这样是会判断不相等的）
        if(!MathUtil.equals(orderDto.getOrderAmount().doubleValue(),payResponse.getOrderAmount())){
            log.error("【微信支付】异步通知，订单金额不一致！ orderId={]，微信通知金额={}，系统金额={}",orderDto.getOrderId(),payResponse.getOrderAmount(),orderDto.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }

        //修改订单支付状态
        orderService.paid(orderDto);
        return payResponse;
    }
}
