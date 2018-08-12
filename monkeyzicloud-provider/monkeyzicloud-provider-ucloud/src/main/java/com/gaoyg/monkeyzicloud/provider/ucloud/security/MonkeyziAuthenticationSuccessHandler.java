package com.gaoyg.monkeyzicloud.provider.ucloud.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaoyg.monkeyzicloud.commom.core.util.RequestUtils;
import com.gaoyg.monkeyzicloud.provider.ucloud.service.UcloudUserService;
import com.gaoyg.monkeyzicloud.util.response.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: 高yg
 * @date: 2018/8/4 20:09
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description: 认证成功后处理用户的信息
 */
@Slf4j
@Component("monkeyziAuthenticationSuccessHandler")
public class MonkeyziAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Resource
    private ObjectMapper objectMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    @Resource
    private ClientDetailsService clientDetailsService;
    @Autowired
    private UcloudUserService ucloudUserService;
    private static final String BEARER_TOKEN_TYPE = "Basic ";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.info("登录成功");
        String header=request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header==null || !header.startsWith(BEARER_TOKEN_TYPE)){
            throw  new UnapprovedClientAuthenticationException("请求头中没有client信息");
        }
        String[] tokens=RequestUtils.extractAndDecodeHeader(header);
        assert tokens.length == 2;
        String clientId=tokens[0];
        String clientSecret=tokens[1];
        ClientDetails clientDetails=clientDetailsService.loadClientByClientId(clientId);
        System.out.println("认证中的secret:"+clientDetails.getClientSecret());
        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
        } /*else if (!StringUtils.equals(clientDetails.getClientSecret(), passwordEncoder.encode(clientSecret))) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
        }*/
        TokenRequest tokenRequest=new TokenRequest(MapUtils.EMPTY_MAP,clientId,clientDetails.getScope(),"custom");
        OAuth2Request oAuth2Request=tokenRequest.createOAuth2Request(clientDetails);
        //认证的信息
        OAuth2Authentication oAuth2Authentication=new OAuth2Authentication(oAuth2Request,authentication);
        //获取token信息,包含access_token,refresh_token等
        OAuth2AccessToken token=authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        User user= (User) authentication.getPrincipal();
        //处理用户的登录信息,保存用户的token等
        ucloudUserService.handlerLoginData(token,user,request);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write((objectMapper.writeValueAsString(R.ok("登录成功",token))));
    }
}
