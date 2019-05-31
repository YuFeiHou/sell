package com.zs.sell.converter;

import com.zs.sell.dataobject.OrderMaster;
import com.zs.sell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fei
 * 日期: 2018-11-07
 * 时间: 0:07
 * 实体转DTO
 */
public class OrderMaster0rderDtoConverter {

    /**
     * 将订单表中的数据copy到DTO中
     * @param orderMaster
     * @return
     */
    public static OrderDto convert(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
