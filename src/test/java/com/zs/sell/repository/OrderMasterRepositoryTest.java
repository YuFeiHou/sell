package com.zs.sell.repository;

import com.zs.sell.dataobject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author fei
 * 日期: 2018-10-18
 * 时间: 17:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;


    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("刘飞");
        orderMaster.setBuyerAddress("北京海淀区303号");
        orderMaster.setBuyerPhone("18203483834");
        orderMaster.setOrderAmount(new BigDecimal(3.2));
        orderMaster.setBuyerOpenid("贾向东");
        orderMaster.setCreateTime(new Date());

        orderMasterRepository.save(orderMaster);
        System.out.println("成功！");
    }


    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0,3);
        Page<OrderMaster> page =  orderMasterRepository.findByBuyerOpenid("贾向东",pageRequest);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
    }
}