package com.gaoyg.monkeyzicloud.provider.ucloud.service;

import com.gaoyg.monkeyzicloud.provider.ucloud.dto.QueryUserParamDto;
import com.gaoyg.monkeyzicloud.provider.ucloud.dto.UserInfoDto;
import com.gaoyg.monkeyzicloud.provider.ucloud.service.hystrix.UcloudUserFeignHystrix;
import com.gaoyg.monkeyzicloud.util.response.R;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: 高yg
 * @date: 2018/7/25 21:37
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@FeignClient(value = "monkeyzicloud-provider-ucloud",fallback = UcloudUserFeignHystrix.class)
public interface UcloudUserFeignApi {
    /**
     * 查询用户的列表
     * @param queryUserParamDto
     * @return
     */
  @PostMapping(value = "/ucloud/user/getUserListPage")
  R getUserListPage(@ApiParam(name = "查询用户列表的参数",value = "用户的列表") @RequestBody QueryUserParamDto queryUserParamDto);

    /**
     * 新增/修改用户的信息
     * @param userInfoDto
     * @return
     */
  @PostMapping(value = "/ucloud/user/save")
  R saveUcloudUser(@ApiParam(name = "save/edit参数",value = "新增或者修改用户") @RequestBody UserInfoDto userInfoDto);
}
