package com.zs.sell.handler;

import com.zs.sell.config.ProjectUrl;
import com.zs.sell.exception.SellException;
import com.zs.sell.exception.SellerAuthorizeException;
import com.zs.sell.utils.ResultUtil;
import com.zs.sell.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author fei
 * @title: ExceptionHandler
 * @projectName sell
 * @description: 异常的捕获
 * @date 2019/5/26 10:49
 */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrl projectUrl;

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerSellException(){
        //做一个页面的跳转
        return new ModelAndView("redirect:"
                .concat(projectUrl.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrl.getSell())
                .concat("/sell/seller/login"));
    }

    /**
     * sell异常处理
     */
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVo hanglerSellerException(SellException e){
        return ResultUtil.Error(e.getCode(),e.getMessage());
    }

}
