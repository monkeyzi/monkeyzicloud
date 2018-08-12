package com.gaoyg.monkeyzicloud.provider.ucloud.security;

import com.gaoyg.monkeyzicloud.security.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author: 高yg
 * @date: 2018/8/5 09:18
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Component
public class FormLoginAuthenticationConfig {

    protected final AuthenticationSuccessHandler monkeyziAuthenticationSuccessHandler;
    protected final AuthenticationFailureHandler monkeyziAuthenticationFailureHandler;
    @Autowired
    public FormLoginAuthenticationConfig(AuthenticationSuccessHandler monkeyziAuthenticationSuccessHandler, AuthenticationFailureHandler monkeyziAuthenticationFailureHandler) {
        this.monkeyziAuthenticationSuccessHandler = monkeyziAuthenticationSuccessHandler;
        this.monkeyziAuthenticationFailureHandler = monkeyziAuthenticationFailureHandler;
    }

    public void configure(HttpSecurity http)throws Exception{
           http.formLogin()
                   .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                   .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                   .successHandler(monkeyziAuthenticationSuccessHandler)
                   .failureHandler(monkeyziAuthenticationFailureHandler);
    }
}
