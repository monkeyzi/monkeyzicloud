package com.gaoyg.monkeyzicloud.provider.ucloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: 高yg
 * @date: 2018/7/19 22:00
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@EnableHystrix
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableSwagger2
public class MonkeyzicloudProviderUcloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonkeyzicloudProviderUcloudApplication.class, args);
    }
}
