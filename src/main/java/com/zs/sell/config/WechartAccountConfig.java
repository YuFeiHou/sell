package com.zs.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author fei
 * @title: WechartAccountConfig
 * @projectName sell
 * @description: 微信账号相关
 * @date 2019/5/17 20:09
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechartAccountConfig {
    /**
     * 微信公众appId
     */
    private  String mpAppId;

    /**
     * 微信公众Secret
     */
    private String  mpAppSecret;

    /**
     * 微信开放平台AppId
     */
    private  String openAppId;

    /**
     *微信开放平台AppSecret
     */
    private String  openAppSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户秘钥
     */
    private String mchKey;

    /**
     * 证书路径
     */
    private String keyPath;

    /**
     *微信支付异步通知（回调地址）
     */
    private String notifyUrl;

    /**
     * 模板id
     */
    private Map<String,String> templateId;
}
