package com.gaoyg.monkeyzicloud.provider.model.dto.group;

import com.gaoyg.monkeyzicloud.provider.model.dto.UserRole.BindUserDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author: 高yg
 * @date: 2018/9/22 21:52
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "GroupBindUserDto")
public class GroupBindUserDto implements Serializable {

    private static final long serialVersionUID = 1383005712348886707L;
    /**
     * 未绑定的用户集合
     */
    @ApiModelProperty(value = "所有用户集合")
    private Set<BindUserDto> allUserSet;

    /**
     * 已经绑定的用户集合
     */
    @ApiModelProperty(value = "已经绑定的用户集合")
    private Set<Long> alreadyBindUserIdSet;

}
