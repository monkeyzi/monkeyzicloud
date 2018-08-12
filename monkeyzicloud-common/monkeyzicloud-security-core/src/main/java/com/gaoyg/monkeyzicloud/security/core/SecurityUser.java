package com.gaoyg.monkeyzicloud.security.core;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author: 高yg
 * @date: 2018/8/11 19:19
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
public class SecurityUser extends User {
    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    private Collection<GrantedAuthority> authorities;
    private String loginName;
    private Long   userId;
    private String loginPwd;
    private String nickName;
    private String status;

    public SecurityUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                        boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities){
      super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
      this.loginName=username;
      this.loginPwd=password;
    }
}
