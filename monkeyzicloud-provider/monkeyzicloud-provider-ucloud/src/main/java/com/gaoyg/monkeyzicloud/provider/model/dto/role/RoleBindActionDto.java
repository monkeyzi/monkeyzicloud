package com.gaoyg.monkeyzicloud.provider.model.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author: 高yg
 * @date: 2018/9/16 21:10
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "角色绑定按钮权限")
public class RoleBindActionDto implements Serializable {

    private static final long serialVersionUID = -8589698204017834593L;
    /**
     * 按钮权限
     */
    @ApiModelProperty(value = "按钮权限")
    private Set<Long> actionIdList;
    /**
     * 角色Id
     */
    @ApiModelProperty(value = "角色Id")
    private Long roleId;
}