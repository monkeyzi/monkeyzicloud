package com.gaoyg.monkeyzicloud.provider.service.hystrix;

import com.gaoyg.monkeyzicloud.provider.dto.QueryUserParamDto;
import com.gaoyg.monkeyzicloud.provider.dto.UserInfoDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudUserFeignApi;
import com.gaoyg.monkeyzicloud.util.response.R;
import org.springframework.stereotype.Component;

/**
 * @author: 高yg
 * @date: 2018/7/25 21:55
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Component
public class UcloudUserFeignHystrix  implements UcloudUserFeignApi {
    @Override
    public R getUserListPage(QueryUserParamDto queryUserParamDTO) {
        return R.error();
    }

    @Override
    public R saveUcloudUser(UserInfoDto userInfoDto) {
        return R.error();
    }
}
