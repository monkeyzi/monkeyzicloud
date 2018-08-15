package com.gaoyg.monkeyzicloud.constant;

/**
 * @author: 高yg
 * @date: 2018/7/21 00:18
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface GlobalConstant {
     /**
      * 本系统的前缀
      */
     String MONKEYZI_PREFIX="monkeyzicloud";
     /**
      * 获取ip的一些常量信息
      */
     String UNKNOWN = "unknown";
     String X_FORWARDED_FOR = "X-Forwarded-For";
     String X_REAL_IP = "X-Real-IP";
     String PROXY_CLIENT_IP = "Proxy-Client-IP";
     String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
     String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
     String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
     String LOCALHOST_IP = "127.0.0.1";
     String LOCALHOST_IP_16 = "0:0:0:0:0:0:0:1";
     int MAX_IP_LENGTH = 15;
     final class Symbol {
          private Symbol() {
          }

          /**
           * The constant COMMA.
           */
          public static final String COMMA = ",";
     }
     final  class  Sys{
          public Sys(){
          }
          /**
           * 全局用户名
           */
          public static final String TOKEN_AUTH_USER = "CURRENT_AUTH_USER";
          /**
           * 超级管理员用户编号
           */
          public static final Long SUPER_MANAGE_USER_ID=1L;
          /**
           * 超级管理员系统登录名
           */
          public static final String  SUPER_MANAGE_LOGIN_NAME="monkeyadmin";
          /**
           * 超级管理员的角色编号
           */
          public static final Long SUER_MANAGE_ROLE_ID=1L;
          /**
           * 超级管理员的组织编号
           */
          public static final Long SUPER_MANAGE_GROUP_ID=1L;

     }
}
