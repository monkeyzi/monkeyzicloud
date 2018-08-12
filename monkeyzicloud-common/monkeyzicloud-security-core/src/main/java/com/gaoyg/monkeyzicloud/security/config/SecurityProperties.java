package com.gaoyg.monkeyzicloud.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: 高yg
 * @date: 2018/8/5 13:40
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@ConfigurationProperties(prefix = "monkeyzicloud.security")
public class SecurityProperties {

    /**
     * OAuth2认证服务器配置
     */
    private OAuth2Properties oauth2 = new OAuth2Properties();


    public OAuth2Properties getOauth2() {
        return oauth2;
    }

    public void setOauth2(OAuth2Properties oauth2) {
        this.oauth2 = oauth2;
    }
}
