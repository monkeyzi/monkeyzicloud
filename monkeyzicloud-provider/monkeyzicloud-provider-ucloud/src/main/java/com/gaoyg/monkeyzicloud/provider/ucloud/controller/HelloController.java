package com.gaoyg.monkeyzicloud.provider.ucloud.controller;

import com.gaoyg.monkeyzicloud.provider.ucloud.service.UcloudUserTokenFeignApi;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 高yg
 * @date: 2018/7/19 22:09
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@RestController
public class HelloController implements UcloudUserTokenFeignApi {
    @Override
    public String getToken() {
        System.out.println("请求来了");
        return "hello world";
    }
}
