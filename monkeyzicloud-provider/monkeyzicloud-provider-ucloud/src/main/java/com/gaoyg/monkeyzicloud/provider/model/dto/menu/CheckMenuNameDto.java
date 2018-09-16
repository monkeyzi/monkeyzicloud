package com.gaoyg.monkeyzicloud.provider.model.dto.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author: 高yg
 * @date: 2018/9/16 15:41
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "CheckMenuNameDto")
public class CheckMenuNameDto implements Serializable {
    private static final long serialVersionUID = 8687848883145768024L;
    /**
     * 菜单的id
     */
    @ApiModelProperty(value = "菜单的id")
    private Long menuId;
    /**
     * 菜单的pid
     */
    @ApiModelProperty(value = "上级菜单ID")
    @NotBlank(message = "上级菜单ID不能为空")
    private Long pid;
    /**
     * 菜单的menuName
     */
    @ApiModelProperty(value = "菜单名称")
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;


}
