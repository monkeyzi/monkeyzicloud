package com.gaoyg.monkeyzicloud.provider.web.rpc;

import com.gaoyg.monkeyzicloud.provider.dto.QueryUserParamDto;
import com.gaoyg.monkeyzicloud.provider.dto.UserInfoDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudUserFeignApi;
import com.gaoyg.monkeyzicloud.util.response.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 高yg
 * @date: 2018/7/25 22:00
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@RestController
@Api(value = "API - UcloudUserFeignApiClient",description = "测试接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudUserFeignApiClient implements UcloudUserFeignApi {
    @Override
    @ApiOperation(httpMethod = "POST", value = "查询用户分页列表")
    public R getUserListPage(@RequestBody QueryUserParamDto queryUserParamDto) {
        return R.ok();
    }

    @Override
    @ApiOperation(httpMethod = "POST",value = "新增或者修改用户的信息")
    public R saveUcloudUser(@RequestBody UserInfoDto userInfoDto) {
        return R.ok();
    }
}
