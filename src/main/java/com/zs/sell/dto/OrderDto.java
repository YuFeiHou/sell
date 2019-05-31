package com.zs.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zs.sell.dataobject.OrderDetail;
import com.zs.sell.dataobject.OrderMaster;
import com.zs.sell.enums.OrderAmountEnum;
import com.zs.sell.enums.PayStatusEnum;
import com.zs.sell.utils.EnumUtil;
import com.zs.sell.utils.serializer.Date2LongSerialize;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用于接收前端传过来的json数据（数据传输）
 * @author fei
 * 日期: 2018-10-18
 * 时间: 20:35
 */
@Data
public class OrderDto {

    /**订单id*/
    private String orderId;
    /**买家名字*/
    private String buyerName;
    /**买家电话*/
    private String buyerPhone;
    /**买家地址*/
    private String buyerAddress;
    /**买家微信openid*/
    private String buyerOpenid;
    /**订单总金额*/
    private BigDecimal orderAmount;
    /**订单状态*/
    private Integer orderStatus;
    /**支付状态*/
    private Integer payStatus;
    /**创建时间*/
    @JsonSerialize(using = Date2LongSerialize.class)
    private Date createTime;
    /**更新时间*/
    @JsonSerialize(using = Date2LongSerialize.class)
    private Date updateTime;
    /**订单详情列表*/
    private List<OrderDetail> orderDetailList;

    //顶单状态(返回字段忽略这个)
    @JsonIgnore
    public OrderAmountEnum getOrderAmountEnum(){
       return EnumUtil.getByCode(orderStatus,OrderAmountEnum.class);
    }

    //支付状态
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
