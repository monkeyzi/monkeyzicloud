package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.annotation.LogAnnotation;
import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudMenu;
import com.gaoyg.monkeyzicloud.provider.model.dto.menu.UcloudEditMenuDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.menu.UcloudMenuStatusDto;
import com.gaoyg.monkeyzicloud.provider.model.vo.menu.ViewMenuVo;
import com.gaoyg.monkeyzicloud.provider.service.UcloudMenuService;
import com.gaoyg.monkeyzicloud.provider.vo.MenuVo;
import com.gaoyg.monkeyzicloud.util.response.R;
import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/9/16 16:05
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/menu",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "web-UcloudMenuController",description = "菜单管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudMenuController extends BaseController {

    @Resource
    private UcloudMenuService ucloudMenuService;

    @PostMapping(value = "/getMenuTree")
    @ApiOperation(httpMethod = "POST", value = "获取菜单树")
    public R getMenuTreeList(){
        List<MenuVo> menuVoList=ucloudMenuService.getMenuVoList(getLoginAuthDto().getUserId(),null);
        return R.ok("查询成功",menuVoList);
    }



    @PostMapping(value = "/queryMenuById/{id}")
    @ApiOperation(httpMethod = "POST", value = "编辑菜单获取详情")
    public R<ViewMenuVo> queryMenuVoById(@ApiParam(name = "id", value = "菜单id") @PathVariable Long id) {
        log.info("修改查询菜单的详情的参数 id={}",id);
        ViewMenuVo menuVo = ucloudMenuService.getViewVoById(id);
        return R.ok("查询成功",menuVo);
    }

    @LogAnnotation
    @PostMapping(value = "/modifyMenuStatus/{id}")
    @ApiOperation(httpMethod = "POST", value = "根据菜单id修改菜单的状态")
    public R modifyMenuStatusById(@ApiParam(name = "ucloudMenuStatusDto", value = "菜单id")
                                                  @RequestBody UcloudMenuStatusDto ucloudMenuStatusDto) {

       log.info("修改菜单的状态的参数为 ucloudMenuStatusDto={}",ucloudMenuStatusDto);
        LoginAuthDto loginAuthDto=getLoginAuthDto();
        ucloudMenuService.updateUcloudMenuStatus(ucloudMenuStatusDto,loginAuthDto);
        return R.ok("操作成功");
    }

    @PostMapping(value = "/save")
    @ApiOperation(httpMethod = "POST", value = "新增/修改菜单")
    @LogAnnotation
    public R  saveMenu(@ApiParam(name = "saveMenu", value = "保存菜单") @RequestBody UcloudEditMenuDto ucloudEditMenuDto) {
        UcloudMenu ucloudMenu = new UcloudMenu();
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        BeanUtils.copyProperties(ucloudEditMenuDto, ucloudMenu);
        ucloudMenuService.saveUcloudMenu(ucloudMenu, loginAuthDto);
        return R.ok("操作成功");
    }

    @PostMapping(value = "/deleteMenuById/{id}")
    @ApiOperation(httpMethod = "POST", value = "删除菜单")
    @LogAnnotation
    public R<Integer> deleteMenuById(@ApiParam(name = "id", value = "菜单id") @PathVariable Long id) {
        log.info(" 根据id删除菜单 id={}", id);
        LoginAuthDto loginAuthDto = getLoginAuthDto();

        Preconditions.checkArgument(id != null, ErrorCodeEnum.UCLOUD10014001.getMsg());
        // 判断此菜单是否有子节点
        boolean hasChild = ucloudMenuService.checkMenuHasChildMenu(id);
        if (hasChild) {
            return R.error(ErrorCodeEnum.UCLOUD10014009.getCode(),ErrorCodeEnum.UCLOUD10014009.getMsg());
        }
        int result = ucloudMenuService.deleteMenuByMenuId(id,loginAuthDto);
        return super.handleResult(result);
    }
}
