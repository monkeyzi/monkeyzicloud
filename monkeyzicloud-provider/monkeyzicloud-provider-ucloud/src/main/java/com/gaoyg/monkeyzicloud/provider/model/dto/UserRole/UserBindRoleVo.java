package com.gaoyg.monkeyzicloud.provider.model.dto.UserRole;

import com.gaoyg.monkeyzicloud.provider.model.dto.role.BindRoleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author: 高yg
 * @date: 2018/8/26 11:21
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "角色绑定用户")
public class UserBindRoleVo implements Serializable {
    private static final long serialVersionUID = -2521583668470612548L;
    /**
     * 未绑定的用户集合
     */
    @ApiModelProperty(value = "所有用户集合")
    private Set<BindRoleDto> allRoleSet;

    /**
     * 已经绑定的用户集合
     */
    @ApiModelProperty(value = "已经绑定的用户集合")
    private Set<Long> alreadyBindRoleIdSet;
}
