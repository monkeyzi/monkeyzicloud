package com.gaoyg.monkeyzicloud.provider.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 高yg
 * @date: 2018/8/26 21:13
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "用户修改密码Dto")
public class UserModifyPwdDto  implements Serializable {
    private static final long serialVersionUID = -3933378415083541145L;
    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名")
    private String loginName;

    /**
     * 原始密码
     */
    @ApiModelProperty(value = "原始密码")
    private String oldPassword;

    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码")
    private String newPassword;

    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码")
    private String confirmPwd;
}
