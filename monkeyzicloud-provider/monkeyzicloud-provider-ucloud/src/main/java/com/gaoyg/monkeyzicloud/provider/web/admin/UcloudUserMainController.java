package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.annotation.LogAnnotation;
import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.provider.exception.UcloudBizException;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudLog;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudUser;
import com.gaoyg.monkeyzicloud.provider.model.dto.UserRole.UserBindRoleVo;
import com.gaoyg.monkeyzicloud.provider.model.dto.user.BindUserRolesDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.user.ModifyUserStatusDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudUserService;
import com.gaoyg.monkeyzicloud.util.response.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author: 高yg
 * @date: 2018/8/22 23:26
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "monkeyzi - UcloudUserMainController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudUserMainController extends BaseController {
    @Autowired
    private UcloudUserService ucloudUserService;

    @PostMapping(value = "/queryUserListWithPage")
    @ApiOperation(httpMethod = "POST",value = "用户列表查询")
    public R queryUserListWithPage(@ApiParam(name = "user",value = "用户列表查询参数") @RequestBody UcloudUser user){
        log.info("用户列表查询参数 user=",user);
        PageInfo pageInfo=ucloudUserService.queryUserListWithPage(user);
        return R.ok("查询成功",pageInfo);
    }

    @LogAnnotation
    @PostMapping(value = "/save")
    @ApiOperation(httpMethod = "POST",value = "新增用户")
    public R saveUser(@ApiParam(name = "user",value = "新增或者修改用户的参数")@RequestBody UcloudUser user){
        log.info("新增用户的参数 user={}",user);
        LoginAuthDto loginAuthDto=getLoginAuthDto();
        ucloudUserService.saveUcloudUser(user,loginAuthDto);
        return R.ok("操作成功");
    }


    @PostMapping(value = "/queryUserLogListWithPage")
    @ApiOperation(httpMethod = "POST",value = "分页查询用户的操作日志")
    public R queryUserLogListWithPage(@ApiParam(name = "ucloudLog",value ="查询用户自己的操作日志" )@RequestBody UcloudLog ucloudLog){
        log.info("分页查询用户操作日志列表 ucloudlog={}",ucloudLog);
        PageHelper.startPage(ucloudLog.getPageNum(), ucloudLog.getPageSize());
        List<UcloudLog> list = ucloudUserService.queryUserLogListWithUserId(getLoginAuthDto().getUserId());
        PageInfo<UcloudLog> pageInfo = new PageInfo<>(list);
        return R.ok("查询成功",pageInfo);
    }

    @LogAnnotation
    @PostMapping(value = "/modifyUserStatus")
    @ApiOperation(httpMethod = "POST",value = "根据用户的Id修改用户的状态")
    public R modifyUserSatus(@ApiParam(name = "modifyUserStatusDto",value = "修改用户状态参数")@RequestBody ModifyUserStatusDto modifyUserStatusDto){
        log.info("修改用户状态参数 modifyUserStatusDto={}",modifyUserStatusDto);
        LoginAuthDto loginAuthDto=getLoginAuthDto();
        UcloudUser user=new UcloudUser();
        user.setStatus(modifyUserStatusDto.getStatus());
        user.setId(modifyUserStatusDto.getUserId());
        int  result=ucloudUserService.modifyUserStatusById(user,loginAuthDto);
        return super.handleResult(result);
    }

    @LogAnnotation
    @PostMapping(value = "/deleteUserById/{userId}")
    @ApiOperation(httpMethod = "POST",value = "删除用户")
    public R deleteUserById(@ApiParam(name = "userId",value = "删除用户参数")@PathVariable Long  userId){
        log.info("删除用户的参数 userId={}",userId);
        int  result=ucloudUserService.deleteUserById(userId);
        return super.handleResult(result);
    }

    @PostMapping(value = "/getBindRole/{userId}")
    @ApiOperation(httpMethod = "POST",value = "获取用户已经绑定的角色列表")
    public R getBindRole(@ApiParam(name = "userId",value = "用户Id")@PathVariable Long userId){
        log.info("获取用户绑定的角色参数 userId={}",userId);
        LoginAuthDto loginAuthDto=getLoginAuthDto();
        Long  currentUserId=loginAuthDto.getUserId();
        if (Objects.equals(userId,currentUserId)){
           throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011023);
        }
        UserBindRoleVo userBindRoleVo=ucloudUserService.getUserBindRoleDto(userId);
        return R.ok("查询成功",userBindRoleVo);
    }

    @LogAnnotation
    @PostMapping(value = "/bindRoles")
    @ApiOperation(httpMethod = "POST",value = "用户绑定角色")
    public R bindRoles(@ApiParam(name = "bindUserRolesDto",value = "用户绑定角色参数")@RequestBody BindUserRolesDto bindUserRolesDto){
       log.info("用户绑定角色参数 bindUserRolesDto={}",bindUserRolesDto);
       LoginAuthDto loginAuthDto=getLoginAuthDto();
       ucloudUserService.bindUserRoles(bindUserRolesDto,loginAuthDto);
       return R.ok("操作成功");
    }

    @LogAnnotation
    @PostMapping(value = "/getUcloudUserById/{userId}")
    @ApiOperation(httpMethod = "POST",value = "根据用户Id查询用户的信息")
    public R getUcloudUserById(@ApiParam(name = "userId",value = "查询用户详细信息的参数-userId")@PathVariable Long  userId){
       log.info("查询用户个人信息的参数 userId={}",userId);
       UcloudUser ucloudUser=ucloudUserService.queryUserById(userId);
       return R.ok("查询Ok",ucloudUser);
    }

    @LogAnnotation
    @PostMapping(value = "/resetLoginPwd/{userId}")
    @ApiOperation(httpMethod = "POST", value = "根据用户Id重置密码")
    public R resetLoginPwd(@ApiParam(name = "userId",value = "修改用户的密码") @PathVariable Long userId){
        log.info("根据用户的Id重置密码参数 useri={}",userId);
        LoginAuthDto loginAuthDto=getLoginAuthDto();
        ucloudUserService.resetLoginPwd(userId,loginAuthDto);
        return R.okMsg("ok");
    }

}
