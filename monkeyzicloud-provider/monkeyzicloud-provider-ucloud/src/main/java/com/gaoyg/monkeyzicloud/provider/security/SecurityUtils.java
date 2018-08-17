package com.gaoyg.monkeyzicloud.provider.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: 高yg
 * @date: 2018/8/14 21:25
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityUtils {

    private static final String  AUTH_LOGIN_AFTER_URL="/user/loginAfter/*";
    private static final String AUTH_LOGOUT_URL="/user/logout";

    /**
     * 获取当前登录的人的用户名
     * @return
     */
    public static String getCurrentLoginName(){
         Object principle=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         if (principle instanceof UserDetails){
             return ((UserDetails) principle).getUsername();
         }
         if (principle instanceof Principal){
             return ((Principal) principle).getName();
         }
         return String.valueOf(principle);
    }

    /**
     * 获取当前登录人的认证的权限
     * @return
     */
    public static Set<String> getCurrentAuthorityUrl(){
         Set<String> set=new HashSet<>();
         Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
         Collection<? extends GrantedAuthority>  authorities=authentication.getAuthorities();
         for (final GrantedAuthority authority:authorities){
             String url=authority.getAuthority();
             if (StringUtils.isNotBlank(url)){
                 set.add(url);
             }
         }
         set.add(AUTH_LOGIN_AFTER_URL);
         set.add(AUTH_LOGOUT_URL);
         return set;
    }
}
