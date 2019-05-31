package com.zs.sell.dataobject;

import com.zs.sell.enums.OrderAmountEnum;
import com.zs.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单表主表（都是关于订单的信息）
 * @author fei
 * 日期: 2018-10-18
 * 时间: 15:59
 */
@Data
@Entity
@DynamicUpdate
public class OrderMaster {

    /**主表id*/
    @Id
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
    /**订单状态,默认0，新下单*/
    private Integer orderStatus = OrderAmountEnum.NEW.getCode();
    /**支付状态，默认0未支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    /**创建时间*/
    private Date createTime;
    /**更新时间*/
    private Date updateTime;
}
