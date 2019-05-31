package com.zs.sell.from;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author fei
 * @title: OrderFrom
 * @projectName sell
 * @description: 表单验证
 * @date 2019/5/17 14:53
 */
@Data
public class OrderFrom {

    /**
     * 买家名称
     */
    @NotEmpty(message = "姓名必填")
    private String name;
    /**
     * 买家联系电话
     */
    @NotEmpty(message = "电话必填")
    private String phone;
    /**
     *买家地址
     */
    @NotEmpty(message = "地址必填")
    private String address;
    /**
     *买家openid
     */
    @NotEmpty(message = "openid必填")
    private String openid;
    /**
     *买家购物车
     */
    @NotEmpty(message = "购物车不能空")
    private String items;
}
