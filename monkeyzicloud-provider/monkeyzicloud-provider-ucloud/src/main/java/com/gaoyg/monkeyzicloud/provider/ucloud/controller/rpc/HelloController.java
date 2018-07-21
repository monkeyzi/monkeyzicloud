package com.gaoyg.monkeyzicloud.provider.ucloud.controller.rpc;

import com.gaoyg.monkeyzicloud.provider.ucloud.service.UcloudUserTokenFeignApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 高yg
 * @date: 2018/7/19 22:09
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@RestController
@Api(value = "API-UcloudUserTokenFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HelloController implements UcloudUserTokenFeignApi {
    @Override
    @ApiOperation(httpMethod = "POST", value = "更新token离线状态")
    public String getToken(String name) {
        System.out.println("请求来了"+name);
        return "hello world:"+name;
    }
}
