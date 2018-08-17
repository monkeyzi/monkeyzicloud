package com.gaoyg.monkeyzicloud.provider.service.hystrix;

import com.gaoyg.monkeyzicloud.provider.dto.UserInfoDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudUserTokenFeignApi;
import com.gaoyg.monkeyzicloud.util.response.R;
import org.springframework.stereotype.Component;

/**
 * @author: 高yg
 * @date: 2018/7/25 21:29
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Component
public class UcloudTokenFeignHystrix implements UcloudUserTokenFeignApi {

    @Override
    public R updateTokenOffLine() {
        return R.error();
    }

    @Override
    public R getToken(UserInfoDto userInfoDto) {
        return R.error();
    }
}
