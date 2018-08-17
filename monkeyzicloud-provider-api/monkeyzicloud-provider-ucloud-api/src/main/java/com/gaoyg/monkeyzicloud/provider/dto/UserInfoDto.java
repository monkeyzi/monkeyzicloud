package com.gaoyg.monkeyzicloud.provider.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author: 高yg
 * @date: 2018/7/25 21:51
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ToString
@ApiModel(value = "用户信息参数",description = "用户对象信息")
public class UserInfoDto {
    @ApiModelProperty(value = "Id")
    private Long id;
    @ApiModelProperty(value = "用户姓名",name = "userName",required = true)
    private String userName;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "登录名")
    private String loginName;
}
