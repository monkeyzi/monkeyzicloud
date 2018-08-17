package com.gaoyg.monkeyzicloud.provider.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/7/31 22:44
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "菜单信息")
public class MenuVo implements Serializable {
    @ApiModelProperty(value = "菜单编号")
    private Long id;
    @ApiModelProperty(value = "菜单父编号")
    private Long pid;
    @ApiModelProperty(value = "菜单编码")
    private String menuCode;
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    @ApiModelProperty(value = "菜单Url")
    private String url;
    @ApiModelProperty(value = "菜单图标")
    private String icon;
    @ApiModelProperty(value = "菜单排序")
    private String number;
    @ApiModelProperty(value = "菜单备注")
    private String remark;
    @ApiModelProperty(value = "菜单状态")
    private String status;
    @ApiModelProperty(value = "菜单父级菜单")
    private MenuVo parentMenu;
    @ApiModelProperty(value = "子菜单列表")
    private List<MenuVo> subMenu;
    @ApiModelProperty(value = "是否有子菜单")
    private boolean hasMenu=false;
}
