package com.zs.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情VO
 * @author fei
 * 日期: 2018-10-18
 * 时间: 9:57
 */
@Data
public class ProductInfoVo {

    /**商品ID */
    @JsonProperty("id")
    private String productId;
    /**商品名称'*/
    @JsonProperty("name")
    private String productName;
    /**商品单价 */
    @JsonProperty("price")
    private BigDecimal productPrice;
    /**描述 */
    @JsonProperty("description")
    private String productDescription;
    /**小图的url */
    @JsonProperty("icon")
    private String productIcon;
}
