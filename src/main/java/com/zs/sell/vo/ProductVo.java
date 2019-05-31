package com.zs.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Data;

import java.util.List;

/**
 * 商品（包含类别）VO
 * @author fei
 * 日期: 2018-10-18
 * 时间: 9:56
 */
@Data
public class ProductVo {

    /**商品类别名称*/
    @JsonProperty("name")
    private String categoryName;
    /**商品类别编号*/
    @JsonProperty("type")
    private Integer categoryType;
    /**商品详情列表*/
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;
}
