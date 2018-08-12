package com.gaoyg.monkeyzicloud.commom.core.annotation;

import com.gaoyg.monkeyzicloud.commom.core.enums.LogTypeEnum;

import java.lang.annotation.*;

/**
 * @author: 高yg
 * @date: 2018/8/12 21:09
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {
    /**
     * 日志类型
     * @return
     */
   LogTypeEnum logType() default LogTypeEnum.OPERATION_LOG;

    /**
     * 是否保存请求参数
     * @return
     */
   boolean isSaveRequestData() default true;

    /**
     * 是否保存响应参数
     * @return
     */
   boolean isSaveResponseData() default true;

}
