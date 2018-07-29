package com.gaoyg.monkeyzicloud.provider.ucloud.web.rpc;

import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.exception.BusinessException;
import com.gaoyg.monkeyzicloud.provider.ucloud.dto.UserInfoDto;
import com.gaoyg.monkeyzicloud.provider.ucloud.service.UcloudUserTokenFeignApi;
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
@Api(value = "API-UcloudUserTokenFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudTokenFeignClient implements UcloudUserTokenFeignApi {

    @Override
    @ApiOperation(httpMethod = "POST", value = "更新token离线状态")
    public R updateTokenOffLine() {
        return R.ok();
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "获取token")
    public R getToken(@ApiParam(name = "userInfoDto",value = "获取用户token") @RequestBody UserInfoDto userInfoDto) {
        //throw new BusinessException(ErrorCodeEnum.UCLOUD00001040);
        return R.ok();
    }
}
