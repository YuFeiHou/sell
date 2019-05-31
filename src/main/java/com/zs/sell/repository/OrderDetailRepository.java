package com.zs.sell.repository;

import com.zs.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单详情表
 * @author fei
 * 日期: 2018-10-18
 * 时间: 16:43
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    /**
     * 根据订单Id查找订单详情
     * @param OrderId
     * @return
     */
    List<OrderDetail> findByOrderId(String OrderId);
}
