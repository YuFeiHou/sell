package com.zs.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author fei
 * @title: WechatPayConfig
 * @projectName sell
 * @description: 微信支付配置
 * @date 2019/5/1819:55
 */
@Component
public class WechatPayConfig {

    @Autowired
        private WechartAccountConfig wechartAccountConfig;

    @Bean
    public BestPayServiceImpl bestPayService(){
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config(){
        WxPayH5Config wxPayH5Config = new WxPayH5Config();

        wxPayH5Config.setAppId(wechartAccountConfig.getMpAppId());
        wxPayH5Config.setAppSecret(wechartAccountConfig.getMpAppSecret());
        wxPayH5Config.setKeyPath(wechartAccountConfig.getKeyPath());
        wxPayH5Config.setMchId(wechartAccountConfig.getMchId());
        wxPayH5Config.setMchKey(wechartAccountConfig.getMchKey());
        wxPayH5Config.setNotifyUrl(wechartAccountConfig.getNotifyUrl());
        return wxPayH5Config;
    }
}
