package com.gaoyg.monkeyzicloud.provider.model.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 高yg
 * @date: 2018/9/2 19:16
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "验证角色编码")
public class CheckRoleCodeDto implements Serializable {
    private static final long serialVersionUID = 6369434659441735160L;
    @ApiModelProperty(value = "角色Id")
    private Long roleId;
    @ApiModelProperty(value = "角色编码")
    private String roleCode;
}
