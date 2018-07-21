package com.gaoyg.monkeyzicloud.provider.ucloud.config;

import com.gaoyg.monkeyzicloud.config.properties.MonkeyziCloudProperties;
import com.gaoyg.monkeyzicloud.constant.GlobalConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 高yg
 * @date: 2018/7/21 11:25
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:配置中心相关配置文件的注入
 */
@Data
@Configuration
@ConfigurationProperties(prefix =GlobalConstant.MONKEYZI_PREFIX)
@EnableConfigurationProperties(MonkeyziCloudProperties.class)
public class MonkeyzicloudConfig {
}
