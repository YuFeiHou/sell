package com.zs.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zs.sell.dataobject.OrderDetail;
import com.zs.sell.dto.OrderDto;
import com.zs.sell.enums.ResultEnum;
import com.zs.sell.exception.SellException;
import com.zs.sell.from.OrderFrom;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fei
 * @title: OrderFrom2OrderDTOConverter
 * @projectName sell
 * @description: 将from表单数据转换为OrderDTO
 * @date 2019/5/1715:26
 */
@Slf4j
public class OrderFrom2OrderDTOConverter {

    public static OrderDto convert(OrderFrom orderFrom) {
        //json转换器
        Gson gson = new Gson();

        OrderDto orderDto = new OrderDto();

        orderDto.setBuyerName(orderFrom.getName());
        orderDto.setBuyerPhone(orderFrom.getPhone());
        orderDto.setBuyerOpenid(orderFrom.getOpenid());
        orderDto.setBuyerAddress(orderFrom.getAddress());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        //json转换
        try {
//            获取json里面list数据的谷歌json获取方式
            orderDetailList = gson.fromJson(orderFrom.getItems(), new TypeToken<List<OrderDetail>>() {}.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误，strig={}", orderFrom.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

}
