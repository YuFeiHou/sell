package com.zs.sell.vo;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * @author fei
 * 日期: 2018-10-18
 * 时间: 9:43
 */
@Data
public class ResultVo <T>{

    /**结果状态码*/
    private Integer code;
    /**结果描述*/
    private String msg;
    /**结果列表*/
    private T Data;
}
