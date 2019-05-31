package com.zs.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author fei
 * @title: JsonUtil
 * @projectName sell
 * @description: Json格式化工具
 * @date 2019/5/18 20:49
 */
public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

}
