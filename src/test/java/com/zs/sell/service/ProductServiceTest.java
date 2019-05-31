package com.zs.sell.service;

import com.zs.sell.dataobject.OrderMaster;
import com.zs.sell.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author fei
 * 日期: 2018-10-17
 * 时间: 23:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findOne() {
       ProductInfo productInfo = productService.findOne("12123");
       System.out.println(productInfo.toString());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> list =  productService.findUpAll();
        System.out.println("商品数量为："+ list.size());
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> productInfos = productService.findAll(request);
        //获得总条数
        System.out.println(productInfos.getTotalElements());
    }

    @Test
    public void save() {

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("956434");
        productInfo.setProductName("鸡火锅");
        productInfo.setProductPrice(new BigDecimal(3.1));
        productInfo.setProductStatus(0);
        productInfo.setProductStock(3);
        productInfo.setProductDescription("拉阿拉");
        productInfo.setCategoryType(1);
        productInfo.setCreateTime(new Date());
        productInfo.setUpdateTime(new Date());

        productService.save(productInfo);
    }

    @Test
    public void onSale(){
        productService.onSale("12123");
    }


    @Test
    public void offSale(){
        productService.offSale("12123");
    }

}