package com.gaoyg.monkeyzicloud.provider.model.domain;

import java.util.Date;

public class MyUser {
    /**
     * ucloud_user.id;ID
     */
    private Long id;

    /**
     * ucloud_user.version;版本号
     */
    private Integer version;

    /**
     * ucloud_user.login_name;登录名
     */
    private String loginName;

    /**
     * ucloud_user.login_pwd;登录密码
     */
    private String loginPwd;

    /**
     * ucloud_user.user_code;工号
     */
    private String userCode;

    /**
     * ucloud_user.user_name;姓名
     */
    private String userName;

    /**
     * ucloud_user.mobile_no;手机号
     */
    private String mobileNo;

    /**
     * ucloud_user.email;邮件地址
     */
    private String email;

    /**
     * ucloud_user.status;状态 1：正常 0：未激活 3：被禁用
     */
    private String status;

    /**
     * ucloud_user.user_source;用户来源
     */
    private String userSource;

    /**
     * ucloud_user.type;操作员类型（2000伙伴，3000客户，1000运营）
     */
    private String type;

    /**
     * ucloud_user.last_login_ip;最后登录IP地址
     */
    private String lastLoginIp;

    /**
     * ucloud_user.last_login_location;最后登录位置
     */
    private String lastLoginLocation;

    /**
     * ucloud_user.remark;描述
     */
    private String remark;

    /**
     * ucloud_user.last_login_time;最后登录时间
     */
    private Date lastLoginTime;

    /**
     * ucloud_user.is_changed_pwd;是否更改过密码
     */
    private Short isChangedPwd;

    /**
     * ucloud_user.pwd_error_count;连续输错密码次数（连续5次输错就冻结帐号）
     */
    private Short pwdErrorCount;

    /**
     * ucloud_user.pwd_error_time;最后输错密码时间
     */
    private Date pwdErrorTime;

    /**
     * ucloud_user.creator;创建人
     */
    private String creator;

    /**
     * ucloud_user.creator_id;创建人ID
     */
    private Long creatorId;

    /**
     * ucloud_user.created_time;创建时间
     */
    private Date createdTime;

    /**
     * ucloud_user.last_operator;最近操作人
     */
    private String lastOperator;

    /**
     * ucloud_user.last_operator_id;最后操作人ID
     */
    private Long lastOperatorId;

    /**
     * ucloud_user.update_time;更新时间
     */
    private Date updateTime;

    /**
     * ucloud_user.account_non_expired;是否过期
     */
    private Byte accountNonExpired;

    /**
     * ucloud_user.enabled;是否可用,对应auth2.0
     */
    private Byte enabled;

    /**
     * ucloud_user.account_non_locked;是否被锁定
     */
    private Byte accountNonLocked;

    /**
     * ucloud_user.credentials_non_expired;证书是否过期
     */
    private Byte credentialsNonExpired;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo == null ? null : mobileNo.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getUserSource() {
        return userSource;
    }

    public void setUserSource(String userSource) {
        this.userSource = userSource == null ? null : userSource.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public String getLastLoginLocation() {
        return lastLoginLocation;
    }

    public void setLastLoginLocation(String lastLoginLocation) {
        this.lastLoginLocation = lastLoginLocation == null ? null : lastLoginLocation.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Short getIsChangedPwd() {
        return isChangedPwd;
    }

    public void setIsChangedPwd(Short isChangedPwd) {
        this.isChangedPwd = isChangedPwd;
    }

    public Short getPwdErrorCount() {
        return pwdErrorCount;
    }

    public void setPwdErrorCount(Short pwdErrorCount) {
        this.pwdErrorCount = pwdErrorCount;
    }

    public Date getPwdErrorTime() {
        return pwdErrorTime;
    }

    public void setPwdErrorTime(Date pwdErrorTime) {
        this.pwdErrorTime = pwdErrorTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getLastOperator() {
        return lastOperator;
    }

    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator == null ? null : lastOperator.trim();
    }

    public Long getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Long lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Byte accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    public Byte getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Byte accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Byte getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Byte credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
}