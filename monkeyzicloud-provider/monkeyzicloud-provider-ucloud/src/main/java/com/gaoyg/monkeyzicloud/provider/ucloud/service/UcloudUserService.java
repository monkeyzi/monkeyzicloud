package com.gaoyg.monkeyzicloud.provider.ucloud.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.provider.ucloud.model.domain.UcloudUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 高yg
 * @date: 2018/8/5 20:23
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudUserService  extends IBaseService<UcloudUser> {
   UcloudUser findByLoginName(String loginName);

   UcloudUser findUserInfoByUserId(Long userId);
   int  updateUserInfo(UcloudUser ucloudUser);
   void handlerLoginData(OAuth2AccessToken token, final User principal, final HttpServletRequest request);
}
