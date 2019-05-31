package com.zs.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情表
 * @author fei
 * 日期: 2018-10-18
 * 时间: 16:27
 */

@Data
@Entity
@DynamicUpdate
public class OrderDetail {

    /**订单详情ID*/
    @Id
    private String detailId;
    /**订单主表id*/
    private String orderId;
    /**商品id*/
    private String productId;
    /**商品名字*/
    private String productName;
    /**商品价格*/
    private BigDecimal productPrice;
    /**商品数量*/
    private Integer productQuantity;
    /**商品小图*/
    private String productIcon;
    /**创建时间*/
    private Date createTime;
    /**更新时间*/
    private Date updateTime;
}
