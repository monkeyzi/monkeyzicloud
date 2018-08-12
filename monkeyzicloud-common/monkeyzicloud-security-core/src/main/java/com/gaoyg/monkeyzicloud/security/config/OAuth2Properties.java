package com.gaoyg.monkeyzicloud.security.config;

import lombok.Data;

/**
 * @author: 高yg
 * @date: 2018/8/5 13:42
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
public class OAuth2Properties {
    /**
     * 使用jwt时为token签名的秘钥
     */
    private String jwtSigningKey = "monkeyzicloud";
    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};
}
