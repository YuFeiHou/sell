package com.zs.sell.utils;

import com.zs.sell.vo.ResultVo;

/**
 * @author fei
 * @title: Result
 * @projectName sell
 * @description: 返回成功的result
 * @date 2019/5/15 13:07
 */
public class ResultUtil {

    /**
     * 功能描述:返回成功时候使用
     * @param:Object
     * @return:ResultVo
     */
    public static ResultVo Success(Object object) {
        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setMsg("成功");
        resultVo.setCode(0);
        return resultVo;
    }
    /**
     * 功能描述:没有传入参数的result
     * @param:
     * @return:
     */
    public static ResultVo Success() {
        return Success(null);
    }

    /**
     * 功能描述:返回错误的摸吧
     * @param code
     * @param msg
     * @return
     */
    public static ResultVo Error(Integer code , String msg ){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }


}
