package com.zs.sell.service;

import com.zs.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @author fei
 * 日期: 2018-10-17
 * 时间: 20:09
 */
public interface CategoryService {

    /**
     * 查询单个商品类别
     * @param categoryId
     * @return
     */
    ProductCategory findOne(Integer categoryId);

    /**
     * 查询所有的商品类别
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 查询满足条件的商品类别
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 插入数据
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
