package com.gaoyg.monkeyzicloud.provider.security;

import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudUser;
import com.gaoyg.monkeyzicloud.provider.service.UcloudUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;

/**
 * @author: 高yg
 * @date: 2018/8/5 20:21
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Component
@Slf4j
public class UcloudUserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UcloudUserService ucloudUserService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户认证");
        Collection<GrantedAuthority> grantedAuthorities;
        UcloudUser user=ucloudUserService.findByLoginName(username);
        if (user==null){
            log.info("用户不存在");
            throw new BadCredentialsException("用户名不存在或者密码错误");
        }
        user=ucloudUserService.findUserInfoByUserId(user.getId());

        boolean enabled=true;
        boolean expired=true;
        boolean ex=true;
        boolean locked=true;

        return new User(user.getLoginName(),passwordEncoder.encode(user.getLoginPwd()),enabled,expired,ex,locked,Collections.EMPTY_LIST);
    }
}
