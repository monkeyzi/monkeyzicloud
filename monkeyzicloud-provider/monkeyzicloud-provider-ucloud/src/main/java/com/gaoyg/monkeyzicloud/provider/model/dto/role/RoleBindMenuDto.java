package com.gaoyg.monkeyzicloud.provider.model.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author: 高yg
 * @date: 2018/9/16 21:48
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "角色绑定菜单参数")
public class RoleBindMenuDto implements Serializable {

    private static final long serialVersionUID = -8589698204017834593L;
    /**
     * 菜单权限
     */
    @ApiModelProperty(value = "菜单权限")
    private Set<Long> menuIdList;
    /**
     * 角色Id
     */
    @ApiModelProperty(value = "角色Id")
    private Long roleId;
}