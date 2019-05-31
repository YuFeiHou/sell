package com.zs.sell.controller;

import com.lly835.bestpay.model.PayResponse;
import com.zs.sell.dataobject.OrderMaster;
import com.zs.sell.dto.OrderDto;
import com.zs.sell.enums.ResultEnum;
import com.zs.sell.exception.SellException;
import com.zs.sell.service.OrderService;
import com.zs.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author fei
 * @title: PayController
 * @projectName sell
 * @description: 支付
 * @date 2019/5/18 19:20
 */
@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl")String returnUrl,
                               Map<String,Object> map){
        //1.查询订单
        OrderDto orderDto =  orderService.findOne(orderId);
        if(orderDto == null){
            log.error("【查询订单】没有此订单，无法支付！ orderDto={}",orderDto);
            throw new SellException(ResultEnum.ORDER_NIT_EXIST);
        }
        //2.发起支付（在业务层完成支付）
        PayResponse payResponse = payService.create(orderDto);

        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);

        return new ModelAndView("pay/create",map);
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){

        //微信异步请求
        payService.notify(notifyData);
        //返回给微信处理结果
        return new ModelAndView("pay/sucess");
    }


}
