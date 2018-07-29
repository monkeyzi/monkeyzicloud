package com.gaoyg.monkeyzicloud.provider.ucloud.web.rpc;

import com.gaoyg.monkeyzicloud.provider.ucloud.dto.QueryUserParamDto;
import com.gaoyg.monkeyzicloud.provider.ucloud.dto.UserInfoDto;
import com.gaoyg.monkeyzicloud.provider.ucloud.service.UcloudUserFeignApi;
import com.gaoyg.monkeyzicloud.util.response.R;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@Api(value = "API - UcloudUserFeignApiClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudUserFeignApiClient implements UcloudUserFeignApi {
    @Override
    @ApiOperation(httpMethod = "POST", value = "查询用户分页列表")
    public R getUserListPage(@RequestBody  QueryUserParamDto queryUserParamDto) {
        return R.ok();
    }

    @Override
    @ApiOperation(httpMethod = "POST",value = "新增或者修改用户的信息")
    public R saveUcloudUser(@RequestBody UserInfoDto userInfoDto) {
        return R.ok();
    }
}
