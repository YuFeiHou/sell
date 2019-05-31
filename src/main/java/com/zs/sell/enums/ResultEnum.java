package com.zs.sell.enums;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Data;

/**
 * @author fei
 * 日期: 2018-11-03
 * 时间: 10:00
 */

public enum ResultEnum {
//    cttl+shift+u->大小之母转小写字母
    PARAM_ERROR(1,"传入参数错误"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品数量不足"),
    ORDER_NIT_EXIST(12,"订单不存在"),
    ORDERDETALI_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDRR_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确！"),
    CART_ERROR(18,"订单中不能没有商品"),
    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),
    WECHAT_MP_ERROR(20,"获取openid错误！"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21,"微信异步通知金额与系统金额不匹配"),
    SUCCESS(22,"您已成功取消订单！"),
    PRODUCT_STATUS_ERROR(23,"商品已经处于上架状态"),
    LOGIN_FAIL(24,"登录失败，用户为非管理员"),
    LOGOUT_SUCCESS(25,"退出登录成功")
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
