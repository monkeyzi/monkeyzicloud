package com.gaoyg.monkeyzicloud.provider.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 高yg
 * @date: 2018/8/26 10:52
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "用户禁用或者启用dto")
public class ModifyUserStatusDto implements Serializable {
    private static final long serialVersionUID = 1494899235149813850L;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;
}
