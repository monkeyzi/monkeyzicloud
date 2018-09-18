package com.gaoyg.monkeyzicloud.provider.model.dto.action;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author: 高yg
 * @date: 2018/9/17 21:05
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "UcloudActionCheckCodeDto")
public class UcloudActionCheckCodeDto implements Serializable {
    private static final long serialVersionUID = 8687848883145768024L;
    /**
     * 权限的id
     */
    @ApiModelProperty(value = "权限的id")
    private Long actionId;
    /**
     * 权限的编码
     */
    @ApiModelProperty(value = "权限编码")
    @NotBlank(message = "权限编码不能为空")
    private String actionCode;


}