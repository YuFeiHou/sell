package com.zs.sell.service;

import com.zs.sell.dataobject.SellerInfo;

/**
 * @author fei
 * @title: SellerInfoService
 * @projectName sell
 * @description: 根据openid查询用户信息
 * @date 2019/5/23 21:26
 */
public interface SellerInfoService {

    SellerInfo findSellerInfoByOpenid(String Openid);

}
