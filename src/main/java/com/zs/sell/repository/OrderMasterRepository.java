package com.zs.sell.repository;

import com.zs.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单表
 * @author fei
 * 日期: 2018-10-18
 * 时间: 16:42
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    /**
     * 根据买家微信号查找
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
