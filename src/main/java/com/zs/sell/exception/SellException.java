package com.zs.sell.exception;

import com.zs.sell.enums.ResultEnum;
import lombok.Data;

/**
 * @author fei
 * 日期: 2018-11-03
 * 时间: 10:08
 */
@Data
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String messsage){
        super(messsage);
        this.code = code;
    }

}
