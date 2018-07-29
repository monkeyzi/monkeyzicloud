package com.gaoyg.monkeyzicloud.provider.ucloud.config;

import com.gaoyg.monkeyzicloud.commom.core.config.MonkeyzicloudObjectMapper;
import com.gaoyg.monkeyzicloud.commom.core.config.SwaggerConfiguration;
import com.gaoyg.monkeyzicloud.commom.core.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/7/21 00:00
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:ucloud
 */
@EnableWebMvc
@Configuration
@Import(SwaggerConfiguration.class)
public class UcloudWebMvcConfig  implements WebMvcConfigurer {

    @Resource
    private TokenInterceptor tokenInterceptor;

    /**
     * swagger 静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/");
    }

    /**
     * token 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                  //拦截的请求
                  .addPathPatterns("/**")
                  //将静态资源以及不需要拦截的请求放行
                  .excludePathPatterns("/swagger-resources/**", "*.js", "/**/*.js",
                          "*.css", "/**/*.css", "*.html", "/**/*.html");
    }

    /**
     * 用于解决客户端响应的中文变成问号的问题
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MonkeyzicloudObjectMapper.buidMvcMessageConverter(converters);
    }
}
