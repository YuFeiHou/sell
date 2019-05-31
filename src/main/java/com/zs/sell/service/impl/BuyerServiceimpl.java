package com.zs.sell.service.impl;

import com.zs.sell.dto.OrderDto;
import com.zs.sell.enums.ResultEnum;
import com.zs.sell.exception.SellException;
import com.zs.sell.service.BuyerService;
import com.zs.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fei
 * @title: BuyerServiceimpl
 * @projectName sell
 * @description: 检测用户
 * @date 2019/5/17 17:44
 */
@Service
@Slf4j
public class BuyerServiceimpl implements BuyerService {

    @Autowired OrderService orderService;


    @Override
    public OrderDto findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDto cancelOrder(String openid, String orderId) {
        OrderDto orderDto = checkOrderOwner(openid,orderId);
        if(orderDto == null){
            log.error("【取消订单】查不到订单，,orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NIT_EXIST);
        }
        return orderService.cancel(orderDto);
    }

    private OrderDto checkOrderOwner(String openid, String orderId){
        OrderDto orderDto =  orderService.findOne(orderId);
        if(orderDto == null){
            return null;
        }
        if(!orderDto.getBuyerOpenid().equalsIgnoreCase(openid)){
             log.error("【查询订单】订单openId不一致，openid={},orderDto={}",openid,orderDto);
             throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDto;
    }
}
