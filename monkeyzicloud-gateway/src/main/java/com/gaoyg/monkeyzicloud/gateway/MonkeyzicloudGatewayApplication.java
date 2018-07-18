package com.gaoyg.monkeyzicloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableHystrix
public class MonkeyzicloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonkeyzicloudGatewayApplication.class, args);
    }
}
