package com.zs.sell.dto;

import lombok.Data;

/**
 * @author fei
 * 加减库存
 * 日期: 2018-11-03
 * 时间: 12:20
 */
@Data
public class CartDto {

    /**商品id*/
    private String productId;
    /**商品数量*/
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
