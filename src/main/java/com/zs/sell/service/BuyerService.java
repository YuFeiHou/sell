package com.zs.sell.service;

import com.zs.sell.dto.OrderDto;

/**
 * @author fei
 * @title: BuyerService
 * @projectName sell
 * @description: 买家查询订单检测
 * @date 2019/5/17 17:41
 */
public interface BuyerService {

    /**
     * 查询一个订单
     */
    OrderDto findOrderOne(String openid, String orderId);

    /**
     * 取消订单
     */
    OrderDto cancelOrder(String openid, String orderId);
}
