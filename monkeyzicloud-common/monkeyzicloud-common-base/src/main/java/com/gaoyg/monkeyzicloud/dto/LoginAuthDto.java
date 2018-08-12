package com.gaoyg.monkeyzicloud.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 高yg
 * @date: 2018/7/31 21:14
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "登录人的信息")
public class LoginAuthDto  implements Serializable {
    @ApiModelProperty(value = "登录人Id")
    private Long userId;
    @ApiModelProperty(value = "登录名")
    private String loginName;
    @ApiModelProperty(value = "登录人姓名")
    private String userName;
    @ApiModelProperty(value = "角色编号")
    private Long roleId;
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "组织名称")
    private String groupName;
    @ApiModelProperty(value = "组织编号")
    private Long  groupId;

    public LoginAuthDto(){}
    public LoginAuthDto(Long userId,String loginName,String userName){
        this.userId=userId;
        this.loginName=loginName;
        this.userName=userName;
    }

    public LoginAuthDto(Long userId,String loginName,String userName,Long roleId,String roleName,Long groupId,String groupName){
        this.userId=userId;
        this.loginName=loginName;
        this.userName=userName;
        this.roleId=roleId;
        this.roleName=roleName;
        this.groupId=groupId;
        this.groupName=groupName;
    }
}
