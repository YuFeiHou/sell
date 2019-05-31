package com.zs.sell.service.impl;

import com.zs.sell.dataobject.SellerInfo;
import com.zs.sell.repository.SellerInfoRepository;
import com.zs.sell.service.SellerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fei
 * @title: SellerInfoServiceimpl
 * @projectName sell
 * @description: TODO
 * @date 2019/5/23 21:30
 */
@Service
public class SellerInfoServiceimpl implements SellerInfoService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String Openid) {
        return sellerInfoRepository.findByOpenid(Openid);
    }
}
