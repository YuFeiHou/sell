package com.zs.sell.service.impl;

import com.zs.sell.dto.OrderDto;
import com.zs.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author fei
 * @title: PayServiceimplTest
 * @projectName sell
 * @description: TODO
 * @date 2019/5/1820:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceimplTest {

    @Autowired
    private PayServiceimpl payServiceimpl;

    @Autowired OrderService orderService;


    @Test
    public void create() throws Exception{

        OrderDto orderDto = orderService.findOne("1558081676308167237");
        payServiceimpl.create(orderDto);

    }

}