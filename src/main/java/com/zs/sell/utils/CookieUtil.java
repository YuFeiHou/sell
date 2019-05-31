package com.zs.sell.utils;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fei
 * @title: CookieUtil
 * @projectName sell
 * @description: CookieUtil
 * @date 2019/5/2420:13
 */
public class CookieUtil {

    /**
     * 设置cookie
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletResponse response,
                                 String name,
                                 String value,
                                 int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 获得Cookie
     */
    public static Cookie getCookie(HttpServletRequest request,
                                    String  name){
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if(cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }else {
            return null;
        }
    }

    /**
     * cookie从request中获取的为数组
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request){
        Map<String, Cookie> map = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie: cookies ){
                map.put(cookie.getName(),cookie);
            }
        }
        return map;
    }
}
