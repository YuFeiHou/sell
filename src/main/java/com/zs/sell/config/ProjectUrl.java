package com.zs.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author fei
 * @title: ProjectUrl
 * @projectName sell
 * @description: TODO
 * @date 2019/5/24 16:41
 */
@Data
@Component
//配置文件不可以开头是大写---》projectUrl
@ConfigurationProperties(prefix = "projecturl")
public class ProjectUrl {
    /**
     * 微信公众平台授权url
     */
    private String wechatMpAuthorize;
    /**
     * 微信开放平台授权url
     */
    private String wechatOpenAuthorize;
    /**
     * 点餐系统
     */
    private String sell;

}
