package com.gaoyg.monkeyzicloud.provider.ucloud.model.dto.token;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: 高yg
 * @date: 2018/8/5 08:56
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "token分页查询参数")
public class TokenQueryDto {
    @ApiModelProperty(value = "登录名")
    private String loginName;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "登录状态")
    private Integer status;
}
