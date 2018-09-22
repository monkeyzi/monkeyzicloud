package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.annotation.LogAnnotation;
import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudAction;
import com.gaoyg.monkeyzicloud.provider.model.dto.action.ActionMainQueryDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.action.ModifyActionStatusDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudActionService;
import com.gaoyg.monkeyzicloud.util.response.R;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/9/17 20:54
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */

@Slf4j
@RestController
@RequestMapping(value = "/action",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "web-UcloudActionController",description = "权限管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudActionController extends BaseController {

    @Resource
    private UcloudActionService ucloudActionService;


    @PostMapping(value = "/queryListWithPage")
    @ApiOperation(httpMethod = "POST", value = "查询权限列表")
    public R queryUacActionListWithPage(@ApiParam(name = "action", value = "权限信息")
                                            @RequestBody ActionMainQueryDto action) {

        log.info("查询权限列表参数 action={}", action);
        PageInfo pageInfo = ucloudActionService.queryActionListWithPage(action);
        return R.ok("查询成功",pageInfo);
    }


    @PostMapping(value = "/deleteActionById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除权限")
    @LogAnnotation
    public R deleteUacActionById(@ApiParam(name = "id", value = "权限id") @PathVariable Long id) {
        int result = ucloudActionService.deleteActionById(id);
        return super.handleResult(result);
    }


    @PostMapping(value = "/batchDeleteByIdList")
    @ApiOperation(httpMethod = "POST", value = "批量删除权限")
    @LogAnnotation
    public R batchDeleteByIdList(@ApiParam(name = "deleteIdList", value = "权限Ids") @RequestBody List<Long> deleteIdList) {
        log.info("批量删除权限 idList={}", deleteIdList);
        ucloudActionService.batchDeleteByIdList(deleteIdList);
        return R.ok();
    }


    @PostMapping(value = "/save")
    @ApiOperation(httpMethod = "POST", value = "新增/修改权限")
    @LogAnnotation
    public R save(@ApiParam(name = "action", value = "权限信息参数") @RequestBody UcloudAction action) {
        LoginAuthDto loginAuthDto = super.getLoginAuthDto();
        ucloudActionService.saveAction(action, loginAuthDto);
        return R.ok();
    }



    @PostMapping(value = "/modifyStatus")
    @ApiOperation(httpMethod = "POST", value = "根据权限Id修改权限状态")
    @LogAnnotation
    public R  modifyActionStatus(@ApiParam(name = "modifyActionStatus", value = "修改权限状态") @RequestBody ModifyActionStatusDto modifyStatusDto) {
        log.info("根据权限Id修改权限状态 modifyStatusDto={}", modifyStatusDto);
        Long actionId = modifyStatusDto.getId();
        Preconditions.checkArgument(actionId != null, "权限ID不能为空");

        UcloudAction ucloudAction = new UcloudAction();
        ucloudAction.setId(actionId);
        ucloudAction.setStatus(modifyStatusDto.getStatus());
        ucloudAction.setUpdateInfo(getLoginAuthDto());

        int result = ucloudActionService.update(ucloudAction);
        return super.handleResult(result);
    }
}
