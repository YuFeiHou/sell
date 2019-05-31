package com.zs.sell.repository;

import com.zs.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fei
 * 日期: 2018-10-17
 * 时间: 16:06
 */
public interface ProductCategoryRepository  extends JpaRepository<ProductCategory,Integer> {
    /**
     * 根据类目编号查询   in查询：就是符合（）里面
     * 模板：findBy+实体名称
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}

