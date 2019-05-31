package com.zs.sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author fei
 * @title: WechatMpConfig
 * @projectName sell
 * @description: TODO
 * @date 2019/5/17 19:58
 */
@Component
public class WechatMpConfig {

    @Autowired
    private WechartAccountConfig wechartAccountConfig;

    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        ((WxMpInMemoryConfigStorage) wxMpConfigStorage).setAppId(wechartAccountConfig.getMpAppId());
        ((WxMpInMemoryConfigStorage) wxMpConfigStorage).setSecret(wechartAccountConfig.getMpAppSecret());
        return wxMpConfigStorage;
    }

}
