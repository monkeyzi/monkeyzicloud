package com.gaoyg.monkeyzicloud.provider.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @author: 高yg
 * @date: 2018/8/26 22:32
 * @className:CheckNewPasswordDto
 * @description: 
 */
@Data
@ApiModel(value = "校验新密码是否与原始密码相同Dto ")
public class CheckNewPasswordDto implements Serializable {

	private static final long serialVersionUID = 4630716723912494960L;
	/**
	 * 登录名
	 */
	@ApiModelProperty(value = "登录名")
	private String loginName;

	/**
	 * 新的密码
	 */
	@ApiModelProperty(value = "新的密码")
	private String newPassword;
}
