package com.gaoyg.monkeyzicloud.provider.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 高yg
 * @date: 2018/8/26 22:17
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "检查邮箱")
public class CheckEmailDto implements Serializable {
    private static final long serialVersionUID = 3802825234063017559L;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
}
