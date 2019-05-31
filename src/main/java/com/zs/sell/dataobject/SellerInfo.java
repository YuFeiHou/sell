package com.zs.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author fei
 * @title: SellerInfo
 * @projectName sell
 * @description: TODO
 * @date 2019/5/2318:15
 */
@Data
@Entity
public class SellerInfo {

    @Id
    /**
     * 卖家信息表
     */
    private String sellerId;
    private String username;
    private String password;
    private String openid;
    private Date createTime;
    private Date updateTime;
}
