package com.gaoyg.monkeyzicloud.provider.model.dto.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 高yg
 * @date: 2018/9/22 21:19
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "修改组织状态")
public class IdStatusDto implements Serializable {
    private static final long serialVersionUID = -1976690893998068416L;

    @ApiModelProperty(value = "用户ID", required = true)
    private Long id;
    @ApiModelProperty(value = "修改状态", required = true)
    private Integer status;
}
