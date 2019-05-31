package com.zs.sell.controller;

import com.zs.sell.config.ProjectUrl;
import com.zs.sell.constant.RedisConstant;
import com.zs.sell.constant.TokenConstant;
import com.zs.sell.dataobject.SellerInfo;
import com.zs.sell.enums.ResultEnum;
import com.zs.sell.exception.SellException;
import com.zs.sell.service.SellerInfoService;
import com.zs.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author fei
 * @title: SellerUserController
 * @projectName sell
 * @description: 卖家后台管理登录
 * @date 2019/5/24 16:57
 */
@Slf4j
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerInfoService sellerInfoService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ProjectUrl projectUrl;

    /**
     * 登录账号
     */
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openId,
                              HttpServletResponse response,
                              Map<String, Object> map) {
        //1.openid去和数据库里面的数据匹配
        SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(openId);
        if (sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error");
        }
        //2.设置token到redis
        //产生一个token
        String token = UUID.randomUUID().toString();
        //设置token的有效时间
        Integer expire = RedisConstant.EXPIRE;
       //token添加到redis中
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEB_PREFIX, token), openId, expire, TimeUnit.SECONDS);
        //3.设置token到cookie(作为凭证，与后台进行校验)
        CookieUtil.setCookie(response, TokenConstant.TOKEN,token,expire);
        //跳转到列表页面(最好用绝对路径)
        return new ModelAndView("redirect:"+ projectUrl.getSell()+"/sell/seller/order/list");
    }

    /**
     * 登出账号
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletResponse response,
                       HttpServletRequest request,
                       Map<String, Object> map) {
        //1.获取Cookie
         Cookie cookie = CookieUtil.getCookie(request, TokenConstant.TOKEN);
         if(cookie != null){
             //2.清除redis
             redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEB_PREFIX, cookie));
             //3.清除cookie
             CookieUtil.setCookie(response,TokenConstant.TOKEN,null,0);
         }
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
