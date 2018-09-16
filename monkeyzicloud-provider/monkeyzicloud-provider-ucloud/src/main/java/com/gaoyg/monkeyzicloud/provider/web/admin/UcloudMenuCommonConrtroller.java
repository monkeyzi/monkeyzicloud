package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudMenu;
import com.gaoyg.monkeyzicloud.provider.model.dto.menu.CheckMenuCodeDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.menu.CheckMenuNameDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.menu.CheckMenuUrlDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudMenuService;
import com.gaoyg.monkeyzicloud.util.response.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author: 高yg
 * @date: 2018/9/16 15:33
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description: 菜单通用
 */
@Slf4j
@RestController
@RequestMapping(value = "/menu",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "web-UcloudMenuCommonController",description = "菜单通用接口管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudMenuCommonConrtroller extends BaseController {

    @Resource
    private UcloudMenuService ucloudMenuService;


    @PostMapping(value = "/checkMenuCode")
    @ApiOperation(httpMethod = "POST", value = "检测菜单编码是否已存在")
    public R checkUcloudMenuCode(@ApiParam(name="checkMenuCodeDto",value = "菜单ID和编码")
                                        @RequestBody CheckMenuCodeDto checkMenuCodeDto){
        log.info("校验菜单编码的唯一性的参数为 checkMenuCodeDto={}",checkMenuCodeDto);
        Long  id=checkMenuCodeDto.getMenuId();
        String menuCode=checkMenuCodeDto.getMenuCode();
        Example example = new Example(UcloudMenu.class);
        Example.Criteria criteria = example.createCriteria();

        if (id != null) {
            criteria.andNotEqualTo("id", id);
        }
        criteria.andEqualTo("menuCode", menuCode);
        int result = ucloudMenuService.selectCountByExample(example);
        if (result<1) {
            return R.ok("编码可用");
        }else {
            return R.errorMsg("编码已存在");
        }
    }

    @PostMapping(value = "/checkMenuName")
    @ApiOperation(httpMethod = "POST", value = "检测菜单名称是否已存在")
    public R checkUcloudMenuName(@ApiParam(name="checkMenuCodeDto",value = "校验菜单名称唯一性的参数")
                                    @RequestBody @Valid CheckMenuNameDto checkMenuNameDto,BindingResult bindingResult){
        log.info("校验菜单名称的唯一性的参数为 checkMenuNameDto={}",checkMenuNameDto);
        Long  id=checkMenuNameDto.getMenuId();
        String menuCode=checkMenuNameDto.getMenuName();
        Long pid=checkMenuNameDto.getPid();
        Example example = new Example(UcloudMenu.class);
        Example.Criteria criteria = example.createCriteria();

        if (id != null) {
            criteria.andNotEqualTo("id", id);
        }
        criteria.andEqualTo("menuCode", menuCode);
        criteria.andEqualTo("pid", pid);
        int result = ucloudMenuService.selectCountByExample(example);
        if (result<1) {
            return R.ok("名称可用");
        }else {
            return R.errorMsg("菜单名称已存在");
        }
    }


    @PostMapping(value = "/checkMenuUrl")
    @ApiOperation(httpMethod = "POST", value = "检测菜单地址的唯一性")
    public R checkUcloudMenuUrl(@ApiParam(name="checkMenuCodeDto",value = "菜单ID和url")
                                 @RequestBody @Valid CheckMenuUrlDto checkMenuUrlDto, BindingResult bindingResult){
        log.info("校验菜单地址url的唯一性的参数为 checkMenuUrlDto={}",checkMenuUrlDto);
        Long  id=checkMenuUrlDto.getMenuId();
        String url=checkMenuUrlDto.getUrl();
        Example example = new Example(UcloudMenu.class);
        Example.Criteria criteria = example.createCriteria();

        if (id != null) {
            criteria.andNotEqualTo("id", id);
        }
        criteria.andEqualTo("url", url);

        int result = ucloudMenuService.selectCountByExample(example);
        if (result<1) {
            return R.ok("可用");
        }else {
            return R.errorMsg("菜单url已存在");
        }
    }
}
