package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.annotation.LogAnnotation;
import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.group.GroupBindUserDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.group.GroupBindUserReqDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudGroupService;
import com.gaoyg.monkeyzicloud.util.response.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: 高yg
 * @date: 2018/9/22 21:35
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@RestController
@Slf4j
@RequestMapping(value = "/group",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "web-UcloudGroupBindUserController",description = "组织/部门绑定用户管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudGroupBindUserController extends BaseController {

    @Resource
    private UcloudGroupService ucloudGroupService;


    @PostMapping(value = "/getBindUser/{groupId}")
    @ApiOperation(httpMethod = "POST", value = "获取组织绑定用户页面数据")
    public R getGroupBindUserPageInfo(@ApiParam(name = "groupId", value = "组织id") @PathVariable Long groupId) {
        log.info("查询组织绑定用户页面数据 groupId={}", groupId);
        LoginAuthDto loginAuthDto = super.getLoginAuthDto();
        Long currentUserId = loginAuthDto.getUserId();
        GroupBindUserDto bindUserDto = ucloudGroupService.getGroupBindUserData(groupId, currentUserId);
        return R.ok(bindUserDto);
    }


    @PostMapping(value = "/bindUser")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "组织绑定用户")
    public R bindUser4Role(@ApiParam(name = "uacGroupBindUserReqDto", value = "组织绑定用户") @RequestBody GroupBindUserReqDto groupBindUserReqDto) {
        log.info("组织绑定用户...  groupBindUserReqDto={}", groupBindUserReqDto);
        LoginAuthDto loginAuthDto = super.getLoginAuthDto();
        ucloudGroupService.groupBindUser(groupBindUserReqDto, loginAuthDto);
        return R.ok();
    }
}
