package com.gaoyg.monkeyzicloud.util.pubutils;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: 高yg
 * @date: 2018/8/12 15:28
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisKeyUtil {
    private static final String ACCESS_TOKEN = "monkeyzicloud:token:accessToken";
    public static String getAccessTokenKey(String token) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(token), "非法请求token参数不存在");
        return ACCESS_TOKEN + ":" + token;
    }
}
