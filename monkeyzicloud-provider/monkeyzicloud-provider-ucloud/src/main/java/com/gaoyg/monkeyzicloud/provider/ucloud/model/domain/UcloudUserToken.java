package com.gaoyg.monkeyzicloud.provider.ucloud.model.domain;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.BaseEntity;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author: 高yg
 * @date: 2018/8/5 08:39
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@Table(name="ucloud_user_token")
@Alias("ucloudUserToken")
public class UcloudUserToken  extends BaseEntity {
    private static final long serialVersionUID = 4341237600124353997L;
    private Long pid;
    /**
     * 用户名
     */
    @Column(name="user_name")
    private String userName;
    /**
     * 登录名
     */
    @Column(name="login_name")
    private String loginName;
    /**
     * 用户编号
     */
    @Column(name="user_id")
    private Long userId;
    /**
     * 操作系统
     */
    @Column(name="os")
    private String os;
    /**
     * 浏览器类型
     */
    private String browser;
    /**
     * 登陆人Ip地址
     */
    @Column(name = "login_ip")
    private String loginIp;

    /**
     * 登录地址
     */
    @Column(name = "login_location")
    private String loginLocation;

    /**
     * 登录地址
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 访问token
     */
    @Column(name = "access_token")
    private String accessToken;

    /**
     * 刷新token
     */
    @Column(name = "refresh_token")
    private String refreshToken;
    /**
     * token类型
     */
    @Column(name="token_type")
    private String tokenType;
    /**
     * 访问token的生效时间(秒)
     */
    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;

    /**
     * 刷新token的生效时间(秒)
     */
    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    /**
     * 0 在线 10已刷新 20 离线
     */
    private Integer status;

    /**
     * 组织流水号
     */
    @Column(name = "group_id")
    private Long groupId;

    /**
     * 组织名称
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 失效时间(秒)
     */
    @Transient
    private Integer expiresIn;
}
