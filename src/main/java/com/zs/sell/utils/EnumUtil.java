package com.zs.sell.utils;

import com.zs.sell.enums.CodeEnum;

/**
 * @author fei
 * @title: EnumUtil
 * @projectName sell
 * @description: 枚举
 * @date 2019/5/22 14:43
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for(T each : enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }


}
