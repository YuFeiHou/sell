package com.zs.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.zs.sell.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author fei
 * @title: PayService
 * @projectName sell
 * @description: 微信支付
 * @date 2019/5/18 19:31
 */
public interface PayService {

    /**
     * 创建一个支付
     * @param orderDto
     */
    PayResponse create(OrderDto orderDto);

    /**
     * 微信异步通知
     */

    PayResponse notify (String notifyData);
}
