package com.zs.sell.enums;

import lombok.Getter;

/**
 * @author fei
 * 日期: 2018-10-17
 * 时间: 21:58
 */
@Getter
public enum ProductStatusEnum {

    UP(0,"上架"),
    DOWN(1,"下架")
    ;

    /**
     * 状态值
     */
    private Integer code;
    /**
     * 状态描述
     */
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
