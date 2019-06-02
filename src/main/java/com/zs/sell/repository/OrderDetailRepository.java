package com.zs.sell.repository;

import com.zs.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     *根据订单id查询订单详情
     */
    List<OrderDetail> getByOrderId(String OrderId);

    /**
     *根据订单id查询订单详情 query
     */
    List<OrderDetail> readByOrderId(String OrderId);

    /**
     *根据订单id查询订单详情 query
     */
    List<OrderDetail> queryByOrderId(String OrderId);

    /**
     * 根据订单id或者商品id查找数据
     */
    List<OrderDetail> findByOrderIdOrProductId(String OrderId ,String ProductId);


    /**
     * 在SQL的查询方法上面使用@Query注解，
     * 如涉及到删除和修改在需要加上@Modifying.
     * 也可以根据需要添加 @Transactional 对事物的支持，查询超时的设置等
     */
    @Transactional
    @Modifying
    @Query("update OrderDetail u set u.productName = ?1 where u.orderId = ?2")
    void updateByOrderId(String userName,String OrderId);




    @Transactional
    @Modifying
    @Query("delete from OrderDetail where orderId = ?1")
     void deleteByOrderId(String OrderId);

}
