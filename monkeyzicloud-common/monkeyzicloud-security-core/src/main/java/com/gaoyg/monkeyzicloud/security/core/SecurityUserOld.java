package com.gaoyg.monkeyzicloud.security.core;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author: 高yg
 * @date: 2018/8/4 21:01
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public class SecurityUserOld implements UserDetails {
    private static final long serialVersionUID = 4872628781561412463L;
    private static final String ENABLE = "ENABLE";

    private Collection<GrantedAuthority> authorities;
    private Long userId;
    private String nickName;
    private String loginName;
    private String loginPwd;
    private Long groupId;
    private String groupName;
    private String status;

    public SecurityUserOld(Long userId, String nickName, String loginName, String loginPwd, Long groupId, String groupName){
          this.userId=userId;
          this.nickName=nickName;
          this.loginName=loginName;
          this.loginPwd=loginPwd;
          this.groupId=groupId;
          this.groupName=groupName;
    }


    public SecurityUserOld(Long userId, String nickName, String loginName, String loginPwd, Long groupId, String groupName, String status, Collection<GrantedAuthority> authorities){
        this.userId=userId;
        this.nickName=nickName;
        this.loginName=loginName;
        this.loginPwd=loginPwd;
        this.groupId=groupId;
        this.groupName=groupName;
        this.status=status;
        this.authorities=authorities;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.getLoginPwd();
    }

    @Override
    public String getUsername() {
        return this.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return  StringUtils.equals(this.status, ENABLE);
    }


    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
