package com.gaoyg.monkeyzicloud.provider.ucloud.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 高yg
 * @date: 2018/8/6 23:58
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudPermissionService {
    boolean hasPermission(Authentication authentication, HttpServletRequest request);
}
