package com.gaoyg.monkeyzicloud.provider.ucloud.controller.message;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 高yg
 * @date: 2018/7/21 13:05
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "API-UCloudMessage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MessageController {
    @PostMapping(value = "/getUserInfo")
    @ApiOperation(httpMethod = "POST", value = "获取用户的信息")
    public String getUserInfo(String name) {
        System.out.println("请求来了"+name);
        return "userinfo:"+name;
    }
}
