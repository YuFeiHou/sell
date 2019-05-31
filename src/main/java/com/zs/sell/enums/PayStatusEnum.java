package com.zs.sell.enums;

/**
 * @author fei
 * 日期: 2018-10-18
 * 时间: 16:22
 */
public enum  PayStatusEnum implements CodeEnum{

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),

    ;

    /**状态值*/
    private Integer code;
    /**状态描述*/
    private String message;

    PayStatusEnum(Integer code, String message) {
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
