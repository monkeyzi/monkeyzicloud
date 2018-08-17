package com.gaoyg.monkeyzicloud.provider.model.dto;

import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.provider.vo.MenuVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/7/31 22:20
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "登录成功后首页响应的菜单列表和信息")
public class LoginRespDto {
    @ApiModelProperty(value = "用户信息")
    private LoginAuthDto loginAuthDto;
    @ApiModelProperty(value = "菜单列表")
    private List<MenuVo> menuList;

}
