package com.gaoyg.monkeyzicloud.provider.model.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/9/3 22:33
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "角色绑定用户")
public class RoleBindUserReqDto implements Serializable {
    private static final long serialVersionUID = 89217138744995863L;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "需要绑定的用户ID集合")
    private List<Long> userIdList;
}
