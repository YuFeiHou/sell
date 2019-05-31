package com.zs.sell.repository;
import com.zs.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fei
 * @title: SellerInfoRepository
 * @projectName sell
 * @description: TODO
 * @date 2019/5/2318:19
 */

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
    SellerInfo findByOpenid(String openid);
}
