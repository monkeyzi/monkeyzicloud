package com.gaoyg.monkeyzicloud.provider.ucloud.security;

import com.gaoyg.monkeyzicloud.security.config.OAuth2ClientProperties;
import com.gaoyg.monkeyzicloud.security.config.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: 高yg
 * @date: 2018/8/4 21:44
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
@Component("restClientDetailsServiceImpl")
public class RestClientDetailsServiceImpl implements ClientDetailsService {

    private ClientDetailsService clientDetailsService;
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        log.info("hhh");
        InMemoryClientDetailsServiceBuilder builder = new InMemoryClientDetailsServiceBuilder();
        if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
            for (OAuth2ClientProperties client : securityProperties.getOauth2().getClients()) {
                log.info(client.getClientId());
                log.info(client.getClientSecret());
                builder.withClient(client.getClientId())
                        .secret(passwordEncoder.encode(client.getClientSecret()))
                        .authorizedGrantTypes("refresh_token", "password","client_credentials")
                        .accessTokenValiditySeconds(client.getAccessTokenValidateSeconds())
                        .refreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds())
                        .scopes(client.getScope());
            }
        }
        try {
            clientDetailsService = builder.build();
        } catch (Exception e) {
            log.error("init={}", e.getMessage(), e);
        }
    }
    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        return clientDetailsService.loadClientByClientId(s);
    }

    /*public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("monkeyzicloudClientSecret"));
    }*/
}
