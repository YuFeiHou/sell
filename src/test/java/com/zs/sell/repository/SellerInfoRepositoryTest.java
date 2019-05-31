package com.zs.sell.repository;

import com.zs.sell.dataobject.SellerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author fei
 * @title: SellerInfoRepositoryTest
 * @projectName sell
 * @description: TODO
 * @date 2019/5/2318:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private  SellerInfoRepository sellerInfoRepository;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();

        sellerInfo.setOpenid("cxc45d1cds1c65vd15d");
        sellerInfo.setSellerId("123132");
        sellerInfo.setPassword("132456");
        sellerInfo.setUsername("测试");
        sellerInfoRepository.save(sellerInfo);
    }

    @Test
    public void findByOpenid(){
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("cxc45d1cds1c65vd15d");
        System.out.println("查找"+sellerInfo.toString());
    }
}