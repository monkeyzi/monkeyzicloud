package com.gaoyg.monkeyzicloud.provider.web.rpc;

import com.gaoyg.monkeyzicloud.commom.core.annotation.LogAnnotation;
import com.gaoyg.monkeyzicloud.provider.dto.UserInfoDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudUserTokenFeignApi;
import com.gaoyg.monkeyzicloud.util.response.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 高yg
 * @date: 2018/7/19 22:09
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@RestController
@Api(value = "API-UcloudUserTokenFeignClient",description = "token管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudTokenFeignClient implements UcloudUserTokenFeignApi {

    @Override
    @ApiOperation(httpMethod = "POST", value = "更新token离线状态")
    public R updateTokenOffLine() {
        return R.ok();
    }

    @Override
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "获取token")
    public R<UserInfoDto> getToken(@ApiParam(name = "userInfoDto",value = "用户信息dto",required = true) @RequestBody UserInfoDto userInfoDto) {
        //throw new BusinessException(ErrorCodeEnum.UCLOUD00001040);
        return R.ok(userInfoDto);
    }
}
