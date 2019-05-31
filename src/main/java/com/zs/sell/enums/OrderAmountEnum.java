package com.zs.sell.enums;

import com.lly835.bestpay.rest.type.Get;
import lombok.Data;
import lombok.Getter;

/**
 * @author fei
 * 日期: 2018-10-18
 * 时间: 16:13
 */
@Getter
public enum OrderAmountEnum implements CodeEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;
    /**
     * 状态值
     */
    private Integer code;
    /**
     * 状态描述
     */
    private String message;

    OrderAmountEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    //根据code获取枚举(代码复用性很差，因此丢弃)
//    public static OrderAmountEnum getOrderAmountEnum(Integer code) {
//        for (OrderAmountEnum orderAmountEnum : OrderAmountEnum.values()) {
//            if (orderAmountEnum.code.equals(code)) {
//                return orderAmountEnum;
//            }
//        }
//        return null;
//    }

}
