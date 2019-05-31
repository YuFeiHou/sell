package com.zs.sell.utils;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;

/**
 * @author fei
 * @title: MathUtil
 * @projectName sell
 * @description: 比较两个金额
 * @date 2019/5/20 11:42
 */
public class MathUtil {

    private static  final Double MONEY_RANGE = 0.01;

    public static Boolean equals(Double d1, Double d2){
        Double result = Math.abs(d1-d2);

        if(result < MONEY_RANGE){
            return true;
        }else {
            return false;
        }
    }
}
