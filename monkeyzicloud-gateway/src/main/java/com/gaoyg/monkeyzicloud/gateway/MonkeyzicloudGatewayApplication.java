package com.gaoyg.monkeyzicloud.gateway;

import com.didispace.swagger.butler.EnableSwaggerButler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableSwaggerButler
@ServletComponentScan
public class MonkeyzicloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonkeyzicloudGatewayApplication.class, args);
    }

    @Bean
    public CorsFilter CorsFilter(){
        final UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config=new CorsConfiguration();
        // 允许cookies跨域
        config.setAllowCredentials(true);
        // #允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
        config.addAllowedOrigin("*");
        // #允许访问的头信息,*表示全部
        config.addAllowedHeader("*");
        // 允许提交请求的方法，*表示全部允许
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        // 允许Get的请求方法
        config.addAllowedMethod("GET");
        // 允许PUT的请求方法
        config.addAllowedMethod("PUT");
        // 允许POst的请求方法
        config.addAllowedMethod("POST");
        // 允许DELETE的请求方法
        config.addAllowedMethod("DELETE");
        // 允许PATCH的请求方法
        config.addAllowedMethod("PATCH");
        //将配置注册
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);

    }
}

