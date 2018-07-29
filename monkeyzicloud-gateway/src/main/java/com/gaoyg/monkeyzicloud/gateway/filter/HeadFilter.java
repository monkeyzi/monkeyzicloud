package com.gaoyg.monkeyzicloud.gateway.filter;

import com.gaoyg.monkeyzicloud.commom.core.util.RequestUtils;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.exception.BusinessException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 高yg
 * @date: 2018/7/28 17:30
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Component
@Slf4j
public class HeadFilter extends ZuulFilter {

    private static final String OPTIONS = "OPTIONS";
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        log.info("{网关},网关开始鉴权");
        RequestContext requestContext=RequestContext.getCurrentContext();
        try {
            diFilter(requestContext);
            log.info("{}","网关鉴权结束");
        }catch (Exception e){
            System.out.println(e);
            log.info("exception {}",e);
            throw new BusinessException(ErrorCodeEnum.UCLOUD00001040);
        }
        return null;
    }

    /**
     * 网关过滤执行逻辑
     * @param requestContext
     */
    private void diFilter(RequestContext requestContext) throws ZuulException {
        HttpServletRequest request=requestContext.getRequest();
        String repuestURI=request.getRequestURI();
        if (OPTIONS.equalsIgnoreCase(request.getMethod())){
            return;
        }
        String requestHeader=RequestUtils.getAuthHeader(request);
        log.info("authHeader:{}",requestHeader);
        if (StringUtils.isBlank(requestHeader)){
            //直接抛出异常
            throw new ZuulException("刷新页面重试", 403, "token为空");
        }
        log.info("网关鉴权");
    }
}
