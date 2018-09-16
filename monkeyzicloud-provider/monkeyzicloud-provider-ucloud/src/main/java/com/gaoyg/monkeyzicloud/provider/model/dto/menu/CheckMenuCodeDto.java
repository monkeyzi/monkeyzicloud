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
@ApiModel(value = "CheckMenuCodeDto")
public class CheckMenuCodeDto implements Serializable {
    private static final long serialVersionUID = 8687848883145768024L;
    /**
     * 菜单的id
     */
    @ApiModelProperty(value = "菜单的id")
    private Long menuId;
    /**
     * 菜单的url
     */
    @ApiModelProperty(value = "菜单编码")
    @NotBlank(message = "菜单编码不能为空")
    private String menuCode;


}
