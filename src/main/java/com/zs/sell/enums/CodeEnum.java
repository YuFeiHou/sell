package com.zs.sell.enums;

/**
 * @author fei
 * @title: CodeEnum
 * @projectName sell
 * @description: 根据枚举的类型传入参数
 * @date 2019/5/22 14:38
 */
public interface CodeEnum<T> {

    T getCode();
}
