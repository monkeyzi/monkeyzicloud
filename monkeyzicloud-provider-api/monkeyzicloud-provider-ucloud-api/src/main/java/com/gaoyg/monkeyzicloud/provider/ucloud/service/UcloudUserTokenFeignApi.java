package com.gaoyg.monkeyzicloud.provider.ucloud.service;

import com.gaoyg.monkeyzicloud.provider.ucloud.dto.UserInfoDto;
import com.gaoyg.monkeyzicloud.provider.ucloud.service.hystrix.UcloudTokenFeignHystrix;
import com.gaoyg.monkeyzicloud.util.response.R;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: 高yg
 * @date: 2018/7/19 21:48
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@FeignClient(value = "monkeyzicloud-provider-ucloud",fallback = UcloudTokenFeignHystrix.class)
public interface UcloudUserTokenFeignApi {

    @PostMapping(value = "/ucloud/token/updateTokenOffLine")
    R updateTokenOffLine();

    @PostMapping(value = "/ucloud/token/getToken")
    R getToken(@ApiParam(name = "getToken",value = "获取token") @RequestBody UserInfoDto userInfoDto);

}
