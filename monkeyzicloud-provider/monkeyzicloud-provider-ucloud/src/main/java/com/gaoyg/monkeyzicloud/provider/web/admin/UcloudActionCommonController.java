package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudAction;
import com.gaoyg.monkeyzicloud.provider.model.dto.action.UcloudActionCheckCodeDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.action.UcloudActionCheckUrlDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudActionService;
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
 * @date: 2018/9/17 20:53
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/action",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "web-UcloudActionCommonController",description = "权限通用接口管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudActionCommonController extends BaseController {

    @Resource
    private UcloudActionService ucloudActionService;


    @PostMapping(value = "/checkActionCode")
    @ApiOperation(httpMethod = "POST", value = "检测权限编码是否已存在")
    public R checkActionCode(@ApiParam(name = "uacActionCheckCodeDto", value = "校验权限编码参数")
                                                @RequestBody UcloudActionCheckCodeDto ucloudActionCheckCodeDto) {
        log.info("校验权限编码唯一性 uacActionCheckCodeDto={}", ucloudActionCheckCodeDto);

        Long id = ucloudActionCheckCodeDto.getActionId();
        String actionCode = ucloudActionCheckCodeDto.getActionCode();

        Example example = new Example(UcloudAction.class);
        Example.Criteria criteria = example.createCriteria();

        if (id != null) {
            criteria.andNotEqualTo("id", id);
        }
        criteria.andEqualTo("actionCode", actionCode);

        int result = ucloudActionService.selectCountByExample(example);
        if (result!=0){
            return R.errorMsg("编码已存在");
        }
        return R.okMsg("编码可用");
    }



    @PostMapping(value = "/checkUrl")
    @ApiOperation(httpMethod = "POST", value = "检测权限URL唯一性")
    public R<Boolean> checkActionUrl(@ApiParam(name = "uacActionCheckUrlDto", value = "id与url")
                                         @RequestBody UcloudActionCheckUrlDto ucloudActionCheckUrlDto) {
        log.info("检测权限URL唯一性 uacActionCheckUrlDto={}", ucloudActionCheckUrlDto);

        Long id = ucloudActionCheckUrlDto.getActionId();
        String url = ucloudActionCheckUrlDto.getUrl();

        Example example = new Example(UcloudAction.class);
        Example.Criteria criteria = example.createCriteria();

        if (id != null) {
            criteria.andNotEqualTo("id", id);
        }
        criteria.andEqualTo("url", url);

        int result = ucloudActionService.selectCountByExample(example);
        if (result!=0){
            return R.errorMsg("url已存在");
        }
        return R.okMsg("url可用");
    }
}
