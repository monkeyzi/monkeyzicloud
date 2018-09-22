package com.gaoyg.monkeyzicloud.provider.model.dto.action;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 高yg
 * @date: 2018/9/18 21:30
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "修改权限状态")
public class ModifyActionStatusDto implements Serializable {

    private static final long serialVersionUID = 1494899235149813850L;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "权限ID")
    private Long id;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;
}
