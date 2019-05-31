package com.zs.sell.controller;

import com.zs.sell.dto.OrderDto;
import com.zs.sell.enums.ResultEnum;
import com.zs.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author fei
 * @title: SellerOrderController
 * @projectName sell
 * @description: 卖家后台管理页面接口
 * @date 2019/5/20 21:01
 */
@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 后台类表数据
     *
     * @param page 第几页 ，当前重第一页开始
     * @param size 一页有多少条
     * @return
     */
    @GetMapping("/list")
    public ModelAndView List(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {

        // page默认是从第一页开始
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<OrderDto> orderDtoPage = orderService.findList(pageRequest);
        map.put("orderDtoPage", orderDtoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("pay/list", map);
    }

    /**
     * 取消订单
     *
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        try {
            OrderDto orderDto = orderService.findOne(orderId);
            //取消订单
            orderService.cancel(orderDto);
        } catch (Exception e) {
            log.error("【卖家取消订单】错误，没有此订单");

            map.put("msg", ResultEnum.ORDER_NIT_EXIST.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success");
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderDto orderDto = new OrderDto();
        try {
            orderDto = orderService.findOne(orderId);
        }catch (Exception e){
            log.error("【查询订单详情】错误，没有此订单");

            map.put("msg", ResultEnum.ORDER_NIT_EXIST.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("orderDto",orderDto);
        return new ModelAndView("order/detail");
    }

    @GetMapping("/finsh")
    public ModelAndView finsh(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderDto orderDto = new OrderDto();
        try {
            orderDto = orderService.findOne(orderId);
            orderService.finsh(orderDto);
        }catch (Exception e){
            log.error("【查询订单详情】错误，没有此订单");

            map.put("msg", ResultEnum.ORDER_NIT_EXIST.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success");
    }

    //TODO 商品上下架没有写


}
