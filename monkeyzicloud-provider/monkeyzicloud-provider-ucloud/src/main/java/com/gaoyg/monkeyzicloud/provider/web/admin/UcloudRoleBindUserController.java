package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.annotation.LogAnnotation;
import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.UserRole.RoleBindUserDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.role.RoleBindUserReqDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudRoleService;
import com.gaoyg.monkeyzicloud.util.response.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 高yg
 * @date: 2018/9/3 22:29
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description: 角色绑定用户
 */
@Slf4j
@RestController
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "monkeyzi - UcloudRoleBindUserController",description = "角色绑定用户管理",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudRoleBindUserController extends BaseController {

   @Autowired
   private UcloudRoleService ucloudRoleService;

    @LogAnnotation
    @PostMapping(value = "/bindUser")
    @ApiOperation(httpMethod = "POST", value = "角色绑定用户")
   public R BindUser(@ApiParam(name="roleBindUserReqDto",value = "角色绑定用户参数")
                         @RequestBody RoleBindUserReqDto roleBindUserReqDto){
        log.info("角色绑定用户的参数 roleBindUserReqDto={}",roleBindUserReqDto);
        LoginAuthDto loginAuthDto=getLoginAuthDto();
        //执行绑定操作
        ucloudRoleService.bindUserRole(roleBindUserReqDto,loginAuthDto);
       return R.ok("操作成功");
   }

    @PostMapping(value = "/getBindUser/{roleId}")
    @ApiOperation(httpMethod = "POST", value = "获取角色绑定用户界面的数据")
   public R getBindUser(@ApiParam(name = "roleId", value = "角色id") @PathVariable Long roleId){
        log.info("获取角色绑定用户界面的数据参数  roleId={}",roleId);
        LoginAuthDto loginAuthDto=super.getLoginAuthDto();
        Long currentUserId=loginAuthDto.getUserId();
        //执行查询
        RoleBindUserDto bindUserDto=ucloudRoleService.getRoleBindUserDto(roleId,currentUserId);
        return R.ok("查询成功",bindUserDto);
   }

}
