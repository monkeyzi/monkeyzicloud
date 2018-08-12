package com.gaoyg.monkeyzicloud.provider.ucloud.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaoyg.monkeyzicloud.util.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: 高yg
 * @date: 2018/8/4 21:22
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
@Component("monkeyziAuthenticationFailureHandler")
public class MonkeyziAuthenticationFailureHandler  extends SimpleUrlAuthenticationFailureHandler {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败");

        // 记录失败次数 和原因 ip等信息 5次登录失败,锁定用户, 不允许再次登录

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(R.error(""+exception.getMessage(),null)));
    }
}
