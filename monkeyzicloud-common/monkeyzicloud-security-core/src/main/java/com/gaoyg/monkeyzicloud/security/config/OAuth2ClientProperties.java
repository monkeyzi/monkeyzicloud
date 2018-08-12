package com.gaoyg.monkeyzicloud.security.config;

import lombok.Data;

/**
 * @author: 高yg
 * @date: 2018/8/5 13:43
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
public class OAuth2ClientProperties {
    /**
     * 第三方应用appId
     */
    private String clientId;
    /**
     * 第三方应用appSecret
     */
    private String clientSecret;
    /**
     * 针对此应用发出的token的有效时间
     */
    private int accessTokenValidateSeconds = 7200;

    private int refreshTokenValiditySeconds = 2592000;

    private String scope;
}
