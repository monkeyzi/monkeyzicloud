package com.gaoyg.monkeyzicloud.provider.model.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 高yg
 * @date: 2018/8/22 21:37
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "更新状态")
public class ModifyRoleStatusDto implements Serializable {
    private static final long serialVersionUID = 1494899235149813850L;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Long id;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;
}
