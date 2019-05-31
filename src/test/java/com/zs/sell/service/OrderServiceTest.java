package com.zs.sell.service;

import com.zs.sell.dataobject.OrderDetail;
import com.zs.sell.dto.CartDto;
import com.zs.sell.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.print.attribute.standard.PageRanges;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author fei
 * 日期: 2018-11-06
 * 时间: 22:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("向东");
        orderDto.setBuyerAddress("北京海淀区");
        orderDto.setBuyerPhone("13245542212");
        orderDto.setBuyerOpenid("1101110");

        //购物车
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail ol = new OrderDetail();
        ol.setProductId("956434");
        ol.setProductQuantity(2);
        ol.setProductIcon("http://xxx");
        orderDetails.add(ol);
        orderDto.setOrderDetailList(orderDetails);
        OrderDto result = orderService.create(orderDto);

        log.info("创建订单 result={}",result);
    }

    @Test
    public void findOne() {
        String t= "1557922470887873029";
        OrderDto orderDto = orderService.findOne(t);
        System.out.println("***"+orderDto.toString());
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0,5);
        Page<OrderDto> orderDtoPage =  orderService.findList("ew3euwhd7sjw9diwkq",pageRequest);
        Assert.assertNotEquals(0,orderDtoPage.getTotalElements());
    }

    @Test
    public void cancel() {
        String t= "1557922470887873029";
        OrderDto orderDto = orderService.findOne(t);
        OrderDto result = orderService.cancel(orderDto);
    }

    @Test
    public void finsh() {
        String t= "1557922470887873029";
        OrderDto orderDto = orderService.findOne(t);
        OrderDto result = orderService.finsh(orderDto);
    }

    @Test
    public void paid() {
        String t= "1557922470887873029";
        OrderDto orderDto = orderService.findOne(t);
        OrderDto result = orderService.paid(orderDto);
    }

    @Test
    public void findList1() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDto> orderDtoPage =  orderService.findList(pageRequest);
        Assert.assertNotEquals(0,orderDtoPage.getTotalElements());
    }
}