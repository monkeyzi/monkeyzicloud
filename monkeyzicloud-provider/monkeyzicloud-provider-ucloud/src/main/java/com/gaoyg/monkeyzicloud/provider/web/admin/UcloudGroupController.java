package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.annotation.LogAnnotation;
import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudGroup;
import com.gaoyg.monkeyzicloud.provider.model.dto.group.IdStatusDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudGroupService;
import com.gaoyg.monkeyzicloud.provider.service.UcloudGroupUserService;
import com.gaoyg.monkeyzicloud.provider.vo.MenuVo;
import com.gaoyg.monkeyzicloud.util.response.R;
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
 * @date: 2018/9/18 21:45
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@RestController
@Slf4j
@RequestMapping(value = "/group",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "web-UcloudGroupController",description = "组织/部门管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudGroupController extends BaseController {

    @Resource
    private UcloudGroupService  ucloudGroupService;


    @PostMapping(value = "/getGroupTree")
    @ApiOperation(httpMethod = "POST", value = "获取部门树")
    public R<List<MenuVo>> getTree() {
        Long userId = super.getLoginAuthDto().getUserId();
        List<MenuVo> tree = ucloudGroupService.getGroupTreeListByUserId(userId);
        return R.ok(tree);
    }


    @PostMapping(value = "/deleteById/{id}")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "根据id删除组织")
    public R deleteGroupById(@ApiParam(name = "id", value = "组织id") @PathVariable Long id) {
        log.info(" 根据id删除组织 id={}", id);
        int result = ucloudGroupService.deleteUcloudGroupById(id);
        return super.handleResult(result);
    }


    @PostMapping(value = "/save")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "新增/修改组织信息")
    public R editGroup(@ApiParam(name = "group", value = "组织信息") @RequestBody UcloudGroup group) {
        log.info("新增/修改组织 参数={}",group);
        LoginAuthDto loginAuthDto = super.getLoginAuthDto();
        ucloudGroupService.saveUcloudGroup(group, loginAuthDto);
        return R.ok();
    }



    @PostMapping(value = "/queryGroupById/{id}")
    @ApiOperation(httpMethod = "POST", value = "获取编辑页面数据")
    public R getEditGroupPageInfo(@ApiParam(name = "id", value = "组织id") @PathVariable Long id) {
        log.info("获取组织详情的参数为 id={}",id);
        UcloudGroup ucloudGroup = ucloudGroupService.getGroupById(id);
        return R.ok("查询成功",ucloudGroup);
    }

    @PostMapping(value = "/modifyStatus")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "根据id修改组织状态")
    public R modifyGroupStatus(@ApiParam(name = "modifyGroupStatus", value = "修改组织状态") @RequestBody IdStatusDto idStatusDto) {
        log.info("根据id修改组织状态 idStatusDto={}", idStatusDto);
        UcloudGroup uacGroup = new UcloudGroup();
        uacGroup.setId(idStatusDto.getId());
        LoginAuthDto loginAuthDto = super.getLoginAuthDto();
        Integer status = idStatusDto.getStatus();
        uacGroup.setStatus(status);
        int result = ucloudGroupService.updateUcloudGroupStatusById(idStatusDto, loginAuthDto);
        return super.handleResult(result);
    }
}
