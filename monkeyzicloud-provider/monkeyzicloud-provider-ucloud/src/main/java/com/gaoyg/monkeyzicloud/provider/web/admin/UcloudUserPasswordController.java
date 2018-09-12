package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.annotation.LogAnnotation;
import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.user.UserModifyPwdDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.user.UserRegisterDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudUserService;
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
 * @date: 2018/8/26 21:09
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:用户密码
 */
@RestController
@Slf4j
@RequestMapping(value = "/user", produces = {"application/json;charset=UTF-8"})
@Api(value = "api - UcloudUserPasswordController", description = "密码管理",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudUserPasswordController extends BaseController {
    @Resource
    private UcloudUserService ucloudUserService;

    @PostMapping(value = "/modifyUserPwd")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "用户修改密码")
    public R<Integer> modifyUserPwd(@ApiParam(name = "userModifyPwdDto", value = "用户修改密码Dto") @RequestBody UserModifyPwdDto userModifyPwdDto) {
        log.info("==》vue用户修改密码, userModifyPwdDto={}", userModifyPwdDto);
        log.info("旧密码 oldPassword = {}", userModifyPwdDto.getOldPassword());
        log.info("新密码 newPassword = {}", userModifyPwdDto.getNewPassword());
        log.info("登录名 loginName = {}", userModifyPwdDto.getLoginName());
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        int result = ucloudUserService.userModifyPwd(userModifyPwdDto, loginAuthDto);
        return handleResult(result);
    }

    @PostMapping(value = "/registerUser")
    @ApiOperation(httpMethod = "POST", value = "注册新用户")
    public R registerUser(@ApiParam(name = "registerDto", value = "用户注册Dto") @RequestBody UserRegisterDto registerDto) {
        log.info("vue注册开始。注册参数：{}", registerDto);
        return R.ok("注册成功");
    }
}
