package com.gaoyg.monkeyzicloud.gateway.exception;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 高yg
 * @date: 2018/7/28 22:43
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Component
public class MonkeyzicloudErrorException extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String,Object> errorAttributesData=super.getErrorAttributes(webRequest,includeStackTrace);
        System.out.println(errorAttributesData);
        //状态码
        Integer code=(Integer)errorAttributesData.get("status");
        //异常信息
        String message=(String)errorAttributesData.get("message");
        Map<String,Object> map=new HashMap<>(4);
        map.put("code",code);
        map.put("msg",message);
        map.put("success",false);
        map.put("data",null);
        return map;

    }
}
