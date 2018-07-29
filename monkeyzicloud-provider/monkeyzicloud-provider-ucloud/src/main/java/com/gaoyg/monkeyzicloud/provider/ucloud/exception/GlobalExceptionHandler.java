package com.gaoyg.monkeyzicloud.provider.ucloud.exception;

import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.exception.BusinessException;
import com.gaoyg.monkeyzicloud.util.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: 高yg
 * @date: 2018/7/29 00:14
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@ControllerAdvice
@RestController
@Slf4j
@RequestMapping( value = "/error")
public class GlobalExceptionHandler {

    /**
     * 参数异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R illegalArgumentException(IllegalArgumentException e) {
        return R.error(ErrorCodeEnum.UCLOUD00001039.getCode(),ErrorCodeEnum.UCLOUD00001039.getMsg());
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
     * 404
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public R NoHandlerFoundException(HttpServletResponse response) {
        log.info("404");
        return R.error(404,"访问资源不存在");
    }

    /**
     * 415
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    public R httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return R.error(415, "不支持媒体类型");
    }

    /**
     * 请求的json格式不正确
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public R HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return R.error(400, "json格式错误");
    }


    /**
     * 500错误的处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public R exception(Exception e) {
        log.info("{500 exception}",e);
        return R.error(500,"服务端异常");
    }
}
