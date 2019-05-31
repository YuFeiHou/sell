package com.zs.sell.service;

import com.zs.sell.dto.OrderDto;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

/**
 * @author fei
 * @title: PushMessage
 * @projectName sell
 * @description: 消息推送模板
 * @date 2019/5/26 12:53
 */
public interface PushMessageService {

    /**
     * 订单状态更新消息推送
     * @param orderDto
     */
    public void orderStatus(OrderDto orderDto);
}
