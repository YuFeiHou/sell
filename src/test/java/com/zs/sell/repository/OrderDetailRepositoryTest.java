package com.zs.sell.repository;

import com.zs.sell.dataobject.OrderDetail;
import com.zs.sell.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author fei
 * 日期: 2018-10-18
 * 时间: 19:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("123456");
        orderDetail.setDetailId("321");
        orderDetail.setProductIcon("d://http");
        orderDetail.setProductId("956434");
        orderDetail.setProductName("鸡火锅");
        orderDetail.setProductPrice(new BigDecimal("3.2"));
        orderDetail.setProductQuantity(36);
        orderDetail.setCreateTime(new Date());
        orderDetail.setUpdateTime(new Date());
        orderDetailRepository.save(orderDetail);
    }

    @Test
    public void findByOrderId() {

       List<OrderDetail> list =  orderDetailRepository.findByOrderId("123456");
       System.out.println("消息"+list.toString());
    }

    /**
     * 查找所有列表数据
     */
    @Test
    public void findAll(){
       List<OrderDetail> orderDetailList = orderDetailRepository.findAll();
       for(OrderDetail o :orderDetailList){
           System.out.println(JsonUtil.toJson(o));
       }
    }

    @Test
    public void Count(){
        Long l = orderDetailRepository.count();
        System.out.println("【数据条数】："+l);
    }

    @Test
    public void findByOrderId1() {

        List<OrderDetail> list =  orderDetailRepository.findByOrderId("123456");
        System.out.println("消息"+list.toString());
    }

    @Test
    public void deleteById() {

        orderDetailRepository.deleteById("123123");
//        orderDetailRepository.deleteByOrderId("123456");
    }



}