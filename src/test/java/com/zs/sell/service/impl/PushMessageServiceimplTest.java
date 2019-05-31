package com.zs.sell.service.impl;

import com.zs.sell.dto.OrderDto;
import com.zs.sell.service.OrderService;
import com.zs.sell.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.rmi.runtime.Log;

import static org.junit.Assert.*;

/**
 * @author fei
 * @title: PushMessageServiceimplTest
 * @projectName sell
 * @description: TODO
 * @date 2019/5/2613:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PushMessageServiceimplTest {

    @Autowired
    private PushMessageService pushMessageService;
    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus() {
        OrderDto orderDto = orderService.findOne("1558080957711290871");
        log.info("【订单详情】"+orderDto.toString());
         pushMessageService.orderStatus(orderDto);
    }
}