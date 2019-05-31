package com.zs.sell.aspect;

import com.zs.sell.constant.RedisConstant;
import com.zs.sell.constant.TokenConstant;
import com.zs.sell.exception.SellerAuthorizeException;
import com.zs.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author fei
 * @title: SellerAuthorizeAspect
 * @projectName sell
 * @description: aop拦截器
 * @date 2019/5/26 10:14
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 方法的验证
     *
     * @Pointcut :切入点，具体的路径，细化到名字的前缀
     */
    @Pointcut("execution(public * com.zs.sell.controller.Seller*.*(..))" +
            "&& !execution(public * com.zs.sell.controller.SellerUserController.*(..))")
    public void verify() {
    }

    /**
     * 具体实现
     * Before:操作的顺序，在...之前操作
     */
    @Before("verify()")
    public void doVerify() {
        //1.获取请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //2.查询cookie(便于验证是否登录过)
        Cookie cookie = CookieUtil.getCookie(request, TokenConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录验证】cookie中没有查到数据cookie");
            throw new SellerAuthorizeException();
        }

        //查询redis
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEB_PREFIX, cookie));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录验证】redis中没有查到数据cookie");
            throw new SellerAuthorizeException();
        }
    }
}
