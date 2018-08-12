package com.gaoyg.monkeyzicloud.provider.ucloud.config;

import com.gaoyg.monkeyzicloud.provider.ucloud.security.FormLoginAuthenticationConfig;
import com.gaoyg.monkeyzicloud.provider.ucloud.security.MonkeyziAuthenticationFailureHandler;
import com.gaoyg.monkeyzicloud.provider.ucloud.security.MonkeyziAuthenticationSuccessHandler;
import com.gaoyg.monkeyzicloud.security.authorize.MonkeyziAuthorizeConfigProvider;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author: 高yg
 * @date: 2018/8/6 21:54
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Configuration
@EnableWebSecurity
public class UcloudSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler monkeyziAuthenticationSuccessHandler(){
        return new MonkeyziAuthenticationSuccessHandler();
    }
    @Bean
    public AuthenticationFailureHandler monkeyziAuthenticationFailureHandler(){
        return new MonkeyziAuthenticationFailureHandler();
    }
    @Bean
    public FormLoginAuthenticationConfig formLoginAuthenticationConfig(){
        return new FormLoginAuthenticationConfig(monkeyziAuthenticationSuccessHandler(),monkeyziAuthenticationFailureHandler());
    }

}
