package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.provider.model.dto.log.QueryLogDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudLogService;
import com.gaoyg.monkeyzicloud.util.response.R;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: 高yg
 * @date: 2018/8/13 21:33
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:日志列表查询
 */
@RestController
@RequestMapping(value = "/log",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
@Api(value = "monkeyzicloud-ucloudLogController",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudLogController extends BaseController {

    @Resource
    private UcloudLogService ucloudLogService;

    @PostMapping(value = "/queryLogListPage")
    @ApiOperation(httpMethod = "POST",value = "查询日志列表")
    public R<PageInfo> queryLogListWithPage(@ApiParam(name = "queryLogDto",value = "日志查询") @RequestBody QueryLogDto queryLogDto){
      log.info("查询日志列表参数:quweyLogDto={}",queryLogDto);
      PageInfo pageInfo=ucloudLogService.queryLogWithPage(queryLogDto);
      return R.ok("ok",pageInfo);
    }
}
