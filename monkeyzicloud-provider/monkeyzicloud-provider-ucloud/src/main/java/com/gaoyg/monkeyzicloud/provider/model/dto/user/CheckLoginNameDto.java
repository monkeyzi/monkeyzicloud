package com.gaoyg.monkeyzicloud.provider.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 高yg
 * @date: 2018/8/26 22:00
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "校验真实姓名唯一性Dto ")
public class CheckLoginNameDto  implements Serializable {
    private static final long serialVersionUID = 3802825234063017559L;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "用户姓名")
    private String loginName;
}
