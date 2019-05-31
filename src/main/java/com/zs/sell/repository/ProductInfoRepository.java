package com.zs.sell.repository;

import com.zs.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fei
 * 日期: 2018-10-17
 * 时间: 20:44
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    /**
     * 根据上架状态查找商品
     * @param ProductStatus
     * @return
     */
    List<ProductInfo> findByProductStatus (Integer ProductStatus);
}
