package com.gaoyg.monkeyzicloud.provider.exception;

import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 高yg
 * @date: 2018/8/14 21:46
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
public class UcloudBizException extends BusinessException {
     public UcloudBizException(){}
     public UcloudBizException(int code,String msg,Object...args){
         super(code,msg,args);
         log.info("UcloudBizException,code:"+this.getCode()+",msg:+"+this.getMessage());
     }

     public UcloudBizException(int code,String msg){
         super(code,msg);
         log.info("UcloudBizException,code:"+this.getCode()+",msg:"+this.getMessage());
     }

     public UcloudBizException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum);
         log.info("UcloudBizException,errorCodeEnum:"+errorCodeEnum);
     }

     public UcloudBizException(ErrorCodeEnum errorCodeEnum,Object...args){
         super(errorCodeEnum,args);
         log.info("UcloudBizException,errorCodeEnum:"+errorCodeEnum+",args:"+args);
     }
}
