package com.gaoyg.monkeyzicloud.provider.ucloud.service.impl;

import com.gaoyg.monkeyzicloud.provider.ucloud.service.UcloudPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 高yg
 * @date: 2018/8/7 00:00
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Component("permissionService")
@Slf4j
public class UcloudPermissionServiceImpl implements UcloudPermissionService {
    @Override
    public boolean hasPermission(Authentication authentication, HttpServletRequest request) {
        log.info("进来了");
        return false;
    }
}
