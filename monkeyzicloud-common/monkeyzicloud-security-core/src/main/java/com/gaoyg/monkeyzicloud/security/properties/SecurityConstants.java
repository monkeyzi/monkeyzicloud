package com.gaoyg.monkeyzicloud.security.properties;

/**
 * @author: 高yg
 * @date: 2018/8/5 09:26
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface SecurityConstants {
    /**
     * 当请求需要身份认证时，默认跳转的url
     */
    String DEFAULT_UNAUTHENTICATION_URL = "/auth/require";
    /**
     * 默认的用户名密码登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/auth/form";
}
