package com.zs.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品实体
 * @author fei
 * 日期: 2018-10-17
 * 时间: 20:29
 */
@Data
@Entity
@DynamicUpdate
public class ProductInfo {

    @Id
    /**商品ID */
    private String productId;
    /**商品名称' */
    private String productName;
    /**商品单价 */
    private BigDecimal productPrice;
    /**库存*/
    private Integer productStock;
    /**描述 */
    private String productDescription;
    /**小图的url */
    private String productIcon;
    /**类目编号 */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
    /**上架状态*/
    private Integer productStatus;
}
