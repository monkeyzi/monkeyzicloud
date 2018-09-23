package com.gaoyg.monkeyzicloud.gateway.exception;

import com.gaoyg.monkeyzicloud.exception.BusinessException;
import com.gaoyg.monkeyzicloud.util.response.R;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author: 高yg
 * @date: 2018/7/29 15:30
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@ControllerAdvice
@ResponseBody
@RequestMapping("/error")
@Slf4j
public class GatewayException {
    /**
     * 404
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public R NoHandlerFoundException(NoHandlerFoundException e) {
        log.info("网关404");
        return R.error(404,"gateway访问资源不存在");
    }
    /**
     * 请求方法不支持的异常
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public R MethodNotSupportException(HttpRequestMethodNotSupportedException e){
        return R.error(405,"method not support");
    }
    /**
     * 业务异常.
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R businessException(BusinessException e) {
        log.error("gateway business exception");
        return R.error(e.getCode(), e.getMessage());
    }


    /**
     * 业务异常.
     *
     *
     * @return the wrapper
     */
    @ExceptionHandler(ZuulException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R ZuulException(ZuulException e) {
        log.error("gateway ZuulException ");
        return R.error(e.nStatusCode, e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public R Exception(Exception e) {
        log.error("gateway 500");
        return R.error(500, e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public R RuntimeException(RuntimeException e) {
        log.error("gateway 500");
        return R.error(500, e.getMessage());
    }
}
