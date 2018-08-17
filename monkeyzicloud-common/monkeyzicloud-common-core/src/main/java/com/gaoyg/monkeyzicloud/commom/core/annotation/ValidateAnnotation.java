package com.gaoyg.monkeyzicloud.commom.core.annotation;

import java.lang.annotation.*;

/**
 * @author: 高yg
 * @date: 2018/8/16 22:17
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateAnnotation {
    boolean isValidate() default  true;
}
