package com.zs.sell.service.impl;

import com.zs.sell.config.WechartAccountConfig;
import com.zs.sell.dto.OrderDto;
import com.zs.sell.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author fei
 * @title: PushMessageServiceimpl
 * @projectName sell
 * @description: SDK消息推送的使用
 * @date 2019/5/26 12:56
 */
@Slf4j
@Service
public class PushMessageServiceimpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WechartAccountConfig wechartAccountConfig;

    @Override
    public void orderStatus(OrderDto orderDto) {
        //调用SDK发送消息
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(wechartAccountConfig.getTemplateId().get("orderStatus"));
        wxMpTemplateMessage.setToUser("o6oCR0ZptF_U1lkn1LgIST5o-Wwk");

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","亲，记得收货哦！"),
                new WxMpTemplateData("keyword1","湖北xx学院"),
                new WxMpTemplateData("keyword2","11111111111"),
                new WxMpTemplateData("keyword3",orderDto.getOrderId()),
                new WxMpTemplateData("keyword4",orderDto.getOrderAmountEnum().getMessage()),
                new WxMpTemplateData("keyword5","￥"+ orderDto.getOrderAmount()),
                new WxMpTemplateData("remark","欢迎下次光临")
        );
        wxMpTemplateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        } catch (WxErrorException e) {
            log.error("【消息推送】发送失败"+e);
            e.printStackTrace();
        }
    }
}
