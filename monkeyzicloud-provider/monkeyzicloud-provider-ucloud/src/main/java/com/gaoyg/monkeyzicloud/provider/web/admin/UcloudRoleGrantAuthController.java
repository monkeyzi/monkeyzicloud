package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.annotation.LogAnnotation;
import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.provider.model.dto.role.RoleBindActionDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.role.RoleBindMenuDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudRoleService;
import com.gaoyg.monkeyzicloud.util.response.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: 高yg
 * @date: 2018/9/16 21:06
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:角色授权
 */
@Slf4j
@RestController
@RequestMapping(value = "/menu",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "web-UcloudRoleGrantAuthController",description = "角色授权管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudRoleGrantAuthController extends BaseController {

    @Resource
    private UcloudRoleService ucloudRoleService;

    @PostMapping(value = "/bindAction")
    @ApiOperation(httpMethod = "POST", value = "角色分配权限")
    @LogAnnotation
    public R bindAction(@ApiParam(name = "bindAuth", value = "权限信息") @RequestBody RoleBindActionDto roleBindActionDto) {
        log.info("角色分配权限. roleBindActionDto= {}", roleBindActionDto);
        ucloudRoleService.bindAction(roleBindActionDto);
        return R.ok("操作成功");
    }

    @PostMapping(value = "/bindMenu")
    @ApiOperation(httpMethod = "POST", value = "角色分配权限")
    @LogAnnotation
    public R bindMenu(@ApiParam(name = "bindAuth", value = "权限信息") @RequestBody RoleBindMenuDto roleBindMenuDto) {
        log.info("角色分配权限. roleBindMenuDto= {}", roleBindMenuDto);
        ucloudRoleService.bindMenu(roleBindMenuDto);
        return R.ok("操作成功");
    }
}
