package com.gaoyg.monkeyzicloud.provider.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudLog;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudUser;
import com.gaoyg.monkeyzicloud.provider.model.dto.UserRole.UserBindRoleVo;
import com.gaoyg.monkeyzicloud.provider.model.dto.user.BindUserRolesDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.user.UserModifyPwdDto;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/5 20:23
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudUserService  extends IBaseService<UcloudUser> {
   /**
    * find user by loginName
    * @param loginName
    * @return
    */
   UcloudUser findByLoginName(String loginName);

   /**
    * find userInfo By userId
    * @param userId
    * @return
    */
   UcloudUser findUserInfoByUserId(Long userId);

   /**
    * update user
    * @param ucloudUser
    * @return
    */
   int  updateUserInfo(UcloudUser ucloudUser);

   /**
    * 处理用户登录数据
    * @param token
    * @param principal
    * @param request
    */
   void handlerLoginData(OAuth2AccessToken token, final User principal, final HttpServletRequest request);

   /**
    * query user list
    * @param user
    * @return
    */
   PageInfo queryUserListWithPage(UcloudUser user);

   /**
    * save user
    * @param user
    * @param loginAuthDto
    */
   void saveUcloudUser(UcloudUser user, LoginAuthDto loginAuthDto);


   /**
    * 根据用户ID查询用户日志集合.
    *
    * @param userId the user id
    *
    * @return the list
    */
   List<UcloudLog> queryUserLogListWithUserId(Long userId);

   /**
    * change user status  by userId
    * @param user
    * @param loginAuthDto
    * @return
    */
   int modifyUserStatusById(UcloudUser user, LoginAuthDto loginAuthDto);

   /**
    * 根据用户的Id删除用户
    * @param userId
    * @return
    */
   int deleteUserById(Long userId);

   /**
    * 获取用户绑定的角色的列表
    * @param userId
    * @return
    */
   UserBindRoleVo getUserBindRoleDto(Long userId);

   /**
    * query  userBy id
    * @param userId
    * @return
    */
   UcloudUser  queryUserById(Long userId);

   /**
    * 用户绑定角色
    * @param bindUserRolesDto
    * @param loginAuthDto
    */
   void bindUserRoles(BindUserRolesDto bindUserRolesDto, LoginAuthDto loginAuthDto);

    /**
     * 重置密码
     * @param userId
     * @param loginAuthDto
     */
    void resetLoginPwd(Long userId, LoginAuthDto loginAuthDto);

    /**
     * 用户修改自己的密码
     * @param userModifyPwdDto
     * @param authResDto
     * @return
     */
    int userModifyPwd(UserModifyPwdDto userModifyPwdDto, LoginAuthDto authResDto);
}
