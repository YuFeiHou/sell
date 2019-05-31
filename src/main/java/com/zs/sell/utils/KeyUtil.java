package com.zs.sell.utils;

import java.util.Random;

/**
 * @author fei
 * 日期: 2018-11-03
 * 时间: 10:18
 */
public class KeyUtil {

    /**
     * 生成唯一主键  （synchronized，防止多线程的时候时间一致）
     * 时间（毫秒）+随机数（6位）
     */
    public static synchronized String genUniquekey(){

        Random random = new Random();
        Integer number =  random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
