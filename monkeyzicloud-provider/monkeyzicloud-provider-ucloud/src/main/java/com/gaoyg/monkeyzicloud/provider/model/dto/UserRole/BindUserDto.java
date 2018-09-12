package com.gaoyg.monkeyzicloud.provider.model.dto.UserRole;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 高yg
 * @date: 2018/9/9 17:15
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "绑定的用户信息")
public class BindUserDto implements Serializable {

    private static final long serialVersionUID = -3385971785265488527L;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobileNo;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
    /**
     * 是否可以操作
     */
    @ApiModelProperty(value = "是否可以操作")
    private boolean disabled;

}
