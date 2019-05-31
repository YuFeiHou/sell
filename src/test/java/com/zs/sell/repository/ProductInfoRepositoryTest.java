package com.zs.sell.repository;

import com.zs.sell.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author fei
 * 日期: 2018-10-17
 * 时间: 20:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    /**测试查找单个商品方法*/
    @Test
    public void findOneTest(){

    }

    /**测试添加商品*/
    @Test
    public void saveOneTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("95643472");
        productInfo.setProductName("黄焖鸡米饭");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStatus(0);
        productInfo.setProductStock(3);
        productInfo.setProductDescription("拉阿拉");
        productInfo.setCategoryType(1);
        productInfo.setCreateTime(new Date());
        productInfo.setUpdateTime(new Date());

        productInfoRepository.save(productInfo);
    }


    /**测试上架商品列表*/
    @Test
    public void findByProductStatus() {

        List<ProductInfo> list = productInfoRepository.findByProductStatus(0);
        System.out.println("上架商品个数为："+list.size());

    }

}