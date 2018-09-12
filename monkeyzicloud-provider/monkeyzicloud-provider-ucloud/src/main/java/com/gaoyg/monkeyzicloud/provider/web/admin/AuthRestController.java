package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.dto.OperationLogDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: 高yg
 * @date: 2018/8/12 23:16
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/auth")
@Api(value = "monkeyzi-AuthRestController",description = "不需要认证的接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthRestController {
    @Resource
    private UcloudLogService ucloudLogService;

    @PostMapping(value = "/saveLog")
    @ApiOperation(httpMethod = "POST", value = "记录操作日志")
    public Integer saveLog(@RequestBody OperationLogDto operationLogDto) {
        log.info("saveLog - 保存操作日志. operationLogDto={}", operationLogDto);
        return ucloudLogService.saveOperationLog(operationLogDto);
    }
}
