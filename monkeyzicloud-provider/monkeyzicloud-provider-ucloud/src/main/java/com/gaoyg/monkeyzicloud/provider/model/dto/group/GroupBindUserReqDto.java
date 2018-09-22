package com.gaoyg.monkeyzicloud.provider.model.dto.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/9/22 21:38
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "GroupBindUserReqDto")
public class GroupBindUserReqDto implements Serializable {
    private static final long serialVersionUID = 89217138744995863L;

    @ApiModelProperty(value = "组织ID")
    private Long groupId;

    @ApiModelProperty(value = "用户id")
    private List<Long> userIdList;
}
