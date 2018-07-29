package com.gaoyg.monkeyzicloud.commom.core.util;

import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 高yg
 * @date: 2018/7/28 20:02
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:获取request
 */
@Slf4j
public class RequestUtils {


    public static String getAuthHeader(HttpServletRequest request){
        String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(authHeader)){
            return null;
        }
        return authHeader;
    }
}
