package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.annotation.LogAnnotation;
import com.gaoyg.monkeyzicloud.commom.core.annotation.ValidateAnnotation;
import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.commom.core.util.RequestUtils;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.provider.enums.UcloudRoleStatusEnum;
import com.gaoyg.monkeyzicloud.provider.exception.UcloudBizException;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRole;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRoleUser;
import com.gaoyg.monkeyzicloud.provider.model.dto.role.ModifyRoleStatusDto;
import com.gaoyg.monkeyzicloud.provider.model.vo.RoleVo;
import com.gaoyg.monkeyzicloud.provider.service.UcloudRoleService;
import com.gaoyg.monkeyzicloud.provider.service.UcloudRoleUserService;
import com.gaoyg.monkeyzicloud.util.response.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/15 23:00
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "monkeyzi - UcloudRoleController",description = "角色管理",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudRoleController extends BaseController {

    @Autowired
    private UcloudRoleService ucloudRoleService;
    @Autowired
    private UcloudRoleUserService ucloudRoleUserService;


    @PostMapping(value = "/queryRoleListWithPage")
    @ApiOperation(httpMethod = "POST",value = "分页查询角色列表")
    /**
     * 参数验证
     */
    @ValidateAnnotation
    public R<PageInfo<RoleVo>> queryUcloudRoleListWithPage(@ApiParam(name="role",value = "角色")
                                                           @RequestBody @Valid UcloudRole role,
                                                           BindingResult bindingResult){
        log.info("查询角色列表参数 role={}",role);
        PageHelper.startPage(role.getPageNum(),role.getPageSize());
        role.setOrderBy("update_time desc");
        List<RoleVo> roleVoList=ucloudRoleService.queryRoleListWithPage(role);
        return R.ok("ok",new PageInfo<>(roleVoList));
    }

    @LogAnnotation
    @PostMapping(value = "/deleteRoleById/{id}")
    @ApiOperation(httpMethod = "POST",value = "通过Id删除角色")
    public R deleteRoleById(@ApiParam(name = "id",value = "角色编号")@PathVariable Long id){
        log.info("删除角色参数 id={}",id);
        int result=ucloudRoleService.deleteRoleById(id);
        return super.handleResult(result);
    }


    @LogAnnotation
    @PostMapping(value = "/batchdeleteRoleByIds")
    @ApiOperation(httpMethod = "POST",value = "批量删除角色")
    public R batchdeleteRoleByIds(@ApiParam(name = "roleIds",value = "角色编号集合")List<Long> roleIds){
        log.info("批量删除角色的参数 roleIds={}",roleIds);
        ucloudRoleService.batchDeleteByIdList(roleIds);
        return R.ok("删除成功");
    }

   @LogAnnotation
   @PostMapping(value = "/modifyRoleStatus")
   @ApiOperation(httpMethod = "POST",value = "更新角色状态")
   public R modifyRoleStatus(@ApiParam(name="roleStatusDto",value = "更新角色参数")@RequestBody ModifyRoleStatusDto roleStatusDto){
        log.info("更新角色的状态参数  roleStatusDto={} ",roleStatusDto);
        Long roleId=roleStatusDto.getId();
        if (roleId==null){
            throw  new UcloudBizException(ErrorCodeEnum.UCLOUD10012001);
        }
        LoginAuthDto loginAuthDto=getLoginAuthDto();
        Long userId =loginAuthDto.getUserId();
        UcloudRoleUser ru=ucloudRoleUserService.getByUserIdAndRoleId(userId,roleId);
        if (ru!=null&&UcloudRoleStatusEnum.DISABLE.getType().equals(roleStatusDto.getStatus())){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012002);
        }
        UcloudRole role=new UcloudRole();
        role.setStatus(roleStatusDto.getStatus());
        role.setId(roleId);
        int result=ucloudRoleService.update(role);
        return super.handleResult(result);
   }


    @LogAnnotation
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增角色",httpMethod = "POST")
    @ValidateAnnotation
    public R save(@ApiParam(name = "role", value = "角色信息") @RequestBody @Valid UcloudRole role,BindingResult bindingResult){
        LoginAuthDto loginAuthDto=RequestUtils.getLoginUser();
        int result=ucloudRoleService.saveRole(role,loginAuthDto);
        return super.handleResult(result);
    }

}
