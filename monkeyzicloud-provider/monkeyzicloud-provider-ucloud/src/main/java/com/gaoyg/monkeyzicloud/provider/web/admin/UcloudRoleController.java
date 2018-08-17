package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.annotation.LogAnnotation;
import com.gaoyg.monkeyzicloud.commom.core.annotation.ValidateAnnotation;
import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRole;
import com.gaoyg.monkeyzicloud.provider.model.vo.RoleVo;
import com.gaoyg.monkeyzicloud.util.response.R;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: 高yg
 * @date: 2018/8/15 23:00
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@RestController
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "monkeyzi - UcloudRoleController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudRoleController extends BaseController {

    @PostMapping(value = "/queryRoleListWithPage")
    @ApiOperation(httpMethod = "POST",value = "分页查询角色列表")
    @ValidateAnnotation
    @LogAnnotation
    public R<PageInfo<RoleVo>> queryUcloudRoleListWithPage(@ApiParam(name="role",value = "角色") @RequestBody @Valid UcloudRole role,BindingResult bindingResult){

        return R.ok();
    }
}
