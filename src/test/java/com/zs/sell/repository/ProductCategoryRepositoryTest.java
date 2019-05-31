package com.zs.sell.repository;

import com.zs.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author fei
 * 日期: 2018-10-17
 * 时间: 16:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private  ProductCategoryRepository productCategoryRepository;

//    数据的查找
    @Test
    public void findOneTest(){
        ProductCategory productCategory = productCategoryRepository.findById(1).get();
        System.out.print(productCategory.toString());
    }

//    数据的录入
     @Test
    public void saveOneTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("汽车");
        productCategory.setCategoryType(1);
        productCategory.setCreateTime(new Date());

        productCategoryRepository.save(productCategory);
    }
//     数据的更新   需要提供一个ID
    @Test
    public void upDataTest(){
        ProductCategory productCategory = productCategoryRepository.findById(3).get();
        productCategory.setCategoryName("奶茶");

        productCategoryRepository.saveAndFlush(productCategory);
    }
//根据类目编号查询
    @Test
    public void findListTest(){
//        Arrays.asList : 向List容器中添加数据
        List<Integer> list = Arrays.asList(1,8);
        List<ProductCategory> list1 =  productCategoryRepository.findByCategoryTypeIn(list);
        System.out.println("想要的数据个数为："+list1.size());
    }


}