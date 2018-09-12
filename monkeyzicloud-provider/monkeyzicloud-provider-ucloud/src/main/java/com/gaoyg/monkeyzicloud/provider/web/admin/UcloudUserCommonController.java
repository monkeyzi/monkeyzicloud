package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.provider.exception.UcloudBizException;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudUser;
import com.gaoyg.monkeyzicloud.provider.model.dto.user.*;
import com.gaoyg.monkeyzicloud.provider.service.UcloudUserService;
import com.gaoyg.monkeyzicloud.provider.utils.Md5Util;
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
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @author: 高yg
 * @date: 2018/8/26 21:48
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@RestController
@Slf4j
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - UcloudUserCommonController",description = "用户管理通用接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudUserCommonController extends BaseController {

    @Resource
    private UcloudUserService ucloudUserService;

    @PostMapping(value = "/checkLoginName")
    @ApiOperation(httpMethod = "POST",value = "检查用户名的唯一性")
    public R checkLoginName(@ApiParam(name = "checkLoginNameDto",value = "检查用户登录名的唯一性") @RequestBody CheckLoginNameDto checkLoginNameDto){
        log.info("检查用户名的唯一性参数 checkLoginNameDto={}",checkLoginNameDto);
        Long  userId=checkLoginNameDto.getUserId();
        String loginName=checkLoginNameDto.getLoginName();
        Example example = new Example(UcloudUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("loginName", loginName);
        if (userId != null) {
            criteria.andNotEqualTo("id", userId);
        }
        int result=ucloudUserService.selectCountByExample(example);
        if (result>=1){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011025);
        }
        return R.ok("用户名可用");
    }

    @PostMapping(value = "/checkUserName")
    @ApiOperation(httpMethod = "POST",value = "检查真实姓名的唯一性")
    public R checkLoginName(@ApiParam(name="checkUserNameDto",value = "检查真实姓名的唯一性")@RequestBody CheckUserNameDto checkUserNameDto){
        log.info("检查用户名的唯一性参数 checkUserNameDto={}",checkUserNameDto);
        Long  userId=checkUserNameDto.getUserId();
        String userName=checkUserNameDto.getUserName();
        Example example = new Example(UcloudUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", userName);
        if (userId != null) {
            criteria.andNotEqualTo("id", userId);
        }
        int result=ucloudUserService.selectCountByExample(example);
        if (result>=1){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011027);
        }
        return R.ok("姓名可用");
    }


    @PostMapping(value = "/checkEmail")
    @ApiOperation(httpMethod = "POST",value = "检查邮箱的唯一性")
    public R checkEmail(@ApiParam(name="checkEmailDto",value = "检查邮箱的唯一性")@RequestBody CheckEmailDto checkEmailDto){
        log.info("检查用户名的唯一性参数 checkEmailDto={}",checkEmailDto);
        Long  userId=checkEmailDto.getUserId();
        String email=checkEmailDto.getEmail();
        Example example = new Example(UcloudUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email", email);
        if (userId != null) {
            criteria.andNotEqualTo("id", userId);
        }
        int result=ucloudUserService.selectCountByExample(example);
        if (result>=1){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011028);
        }
        return R.ok("邮箱可用");
    }

    @PostMapping(value = "/checkPhone")
    @ApiOperation(httpMethod = "POST",value = "检查手机号的唯一性")
    public R checkEmail(@ApiParam(name="checkUserPhoneDto",value = "检查手机号的唯一性")@RequestBody CheckUserPhoneDto checkUserPhoneDto){
        log.info("检查用户名的唯一性参数 checkEmailDto={}",checkUserPhoneDto);
        Long  userId=checkUserPhoneDto.getUserId();
        String mobileNo=checkUserPhoneDto.getMobileNo();
        Example example = new Example(UcloudUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("mobileNo", mobileNo);
        if (userId != null) {
            criteria.andNotEqualTo("id", userId);
        }
        int result=ucloudUserService.selectCountByExample(example);
        if (result>=1){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011028);
        }
        return R.ok("手机号可用");
    }

    @PostMapping(value = "/checkNewPassword")
    @ApiOperation(httpMethod = "POST", value = "校验新密码是否与原始密码相同")
    public R checkNewPassword(@ApiParam(name = "checkNewPasswordDto", value = "校验新密码是否与原始密码相同Dto") @RequestBody CheckNewPasswordDto checkNewPasswordDto) {
        log.info(" 校验新密码是否与原始密码相同 checkNewPasswordDto={}", checkNewPasswordDto);
        String loginName = checkNewPasswordDto.getLoginName();
        String newPassword = checkNewPasswordDto.getNewPassword();
        UcloudUser  ucloudUser = new UcloudUser();
        ucloudUser.setLoginName(loginName);
        int result = 0;
        UcloudUser user = ucloudUserService.findByLoginName(loginName);
        if (user == null) {
            log.error("找不到用户. loginName={}", loginName);
        } else {
            ucloudUser.setLoginPwd(Md5Util.encrypt(newPassword));
            result = ucloudUserService.selectCount(ucloudUser);
        }
        if (result>=1){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011036);
        }
        return R.ok("新密码可用");
    }
}
