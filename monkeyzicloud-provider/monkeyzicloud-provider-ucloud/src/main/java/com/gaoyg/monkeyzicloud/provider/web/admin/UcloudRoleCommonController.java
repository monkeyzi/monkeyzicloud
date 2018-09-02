package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRole;
import com.gaoyg.monkeyzicloud.provider.model.dto.role.CheckRoleCodeDto;
import com.gaoyg.monkeyzicloud.provider.model.vo.role.BindAuthVo;
import com.gaoyg.monkeyzicloud.provider.service.UcloudRoleService;
import com.gaoyg.monkeyzicloud.util.response.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

/**
 * @author: 高yg
 * @date: 2018/9/2 19:06
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description: 角色管理公共方法
 */
@RestController
@Slf4j
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "monkeyzi - UcloudRoleCommonController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudRoleCommonController  extends BaseController {

    @Autowired
    private UcloudRoleService ucloudRoleService;

    @PostMapping(value = "/queryRoleInfoById/{id}")
    @ApiOperation(httpMethod = "POST", value = "查看角色信息")
    public R queryRoleInfoById(@ApiParam(name="id",value = "角色编号") @PathVariable Long id){
        UcloudRole role=ucloudRoleService.selectByKey(id);
        return R.ok("查询成功",role);
    }

    @PostMapping(value = "/checkRoleCode")
    @ApiOperation(httpMethod = "POST", value = "验证角色编码是否存在")
    public R<Boolean> checkUacRoleCode(@ApiParam(name = "roleCode", value = "角色编码")
                                                 @RequestBody CheckRoleCodeDto checkRoleCodeDto) {
        Long roleId=checkRoleCodeDto.getRoleId();
        String roleCode=checkRoleCodeDto.getRoleCode();
        Example example = new Example(UcloudRole.class);
        Example.Criteria criteria = example.createCriteria();

        if (roleId != null) {
            criteria.andNotEqualTo("id", roleId);
        }
        criteria.andEqualTo("roleCode", roleCode);
        int count=ucloudRoleService.selectCountByExample(example);
        if (count>1){
            return R.errorMsg(ErrorCodeEnum.UCLOUD10012009.getMsg());
        }
        return R.okMsg("该角色编码可用");
    }


    @PostMapping(value = "/getActionTreeByRoleId/{roleId}")
    @ApiOperation(httpMethod = "POST", value = "获取权限树")
    public R getActionTreeByRoleId(@ApiParam(name = "roleId", value = "角色id") @PathVariable Long roleId) {
        log.info("根据角色Id查询权限树的参数  roleId={}",roleId);
        BindAuthVo bindAuthVo = ucloudRoleService.getActionTreeByRoleId(roleId);
        return R.ok("查询成功",bindAuthVo);
    }

    @PostMapping(value = "/getMenuTreeByRoleId/{roleId}")
    @ApiOperation(httpMethod = "POST", value = "获取菜单树")
    public R<BindAuthVo> getMenuTreeByRoleId(@ApiParam(name = "roleId", value = "角色id") @PathVariable Long roleId) {
        log.info("根据角色Id查询菜单树的参数 roleId={}", roleId);
        BindAuthVo bindAuthVo = ucloudRoleService.getMenuTreeByRoleId(roleId);
        return R.ok(bindAuthVo);
    }

}
