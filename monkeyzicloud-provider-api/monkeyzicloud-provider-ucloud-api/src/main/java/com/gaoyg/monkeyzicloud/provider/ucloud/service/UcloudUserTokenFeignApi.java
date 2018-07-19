package com.gaoyg.monkeyzicloud.provider.ucloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author: 高yg
 * @date: 2018/7/19 21:48
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@FeignClient(value = "monkeyzicloud-provider-ucloud")
public interface UcloudUserTokenFeignApi {

    @PostMapping(value = "/api/ucloud/getToken")
    String getToken();
}
