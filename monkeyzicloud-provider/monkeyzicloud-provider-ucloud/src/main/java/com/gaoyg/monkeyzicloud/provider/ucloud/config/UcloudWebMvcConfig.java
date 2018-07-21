package com.gaoyg.monkeyzicloud.provider.ucloud.config;

import com.gaoyg.monkeyzicloud.commom.core.config.MonkeyzicloudObjectMapper;
import com.gaoyg.monkeyzicloud.commom.core.config.SwaggerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/7/21 00:00
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@EnableWebMvc
@Configuration
@Import(SwaggerConfiguration.class)
public class UcloudWebMvcConfig  implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/");
    }

    /**
     * 用于解决客户端响应的中文变成问好的问题
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MonkeyzicloudObjectMapper.buidMvcMessageConverter(converters);
    }
}
