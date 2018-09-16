package com.gaoyg.monkeyzicloud.provider.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author: 高yg
 * @date: 2018/8/5 09:31
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Configuration
@EnableResourceServer
public class UcloudResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
   private MonkeyziAuthenticationSuccessHandler monkeyziAuthenticationSuccessHandler;
    @Autowired
    private FormLoginAuthenticationConfig formLoginAuthenticationConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        formLoginAuthenticationConfig.configure(http);
        http.authorizeRequests()

                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/swagger-resources/**", "*.js", "/**/*.js",
                        "*.css", "/**/*.css","/auth/**", "/v2/api-docs","*.html", "/**/*.html","/webjars/**").permitAll()

                .anyRequest().authenticated()
                .and().httpBasic()
                .and().csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }




}
