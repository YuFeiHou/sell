package com.zs.sell.service;

import com.zs.sell.dataobject.ProductInfo;
import com.zs.sell.dto.CartDto;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @author fei
 * 日期: 2018-10-17
 * 时间: 21:40
 */
public interface ProductService {

    /**
     * 根据商品ID获取商品
     * @param productId
     * @return
     */
    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询所有商品（可以分页）
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(org.springframework.data.domain.Pageable pageable);

    /**
     * 添加商品
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     * @param cartDtoList
     * 参数描述：用户下订单所购买的商品列表
     */
    void increaseStock(List<CartDto> cartDtoList);

    /**
     * 减库存
     */
    void decreaseStock(List<CartDto> cartDtoList );

    /**
     * 商品的上架
     * @param ProductId
     * @return
     */
    ProductInfo onSale(String ProductId);

    /**
     * 商品的下架
     * @param ProductId
     * @return
     */
    ProductInfo offSale(String ProductId);
}
