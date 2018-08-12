package com.gaoyg.monkeyzicloud.provider.ucloud.config;

import com.gaoyg.monkeyzicloud.commom.core.aspect.LogAspect;
import com.gaoyg.monkeyzicloud.config.properties.MonkeyziCloudProperties;
import com.gaoyg.monkeyzicloud.constant.GlobalConstant;
import com.gaoyg.monkeyzicloud.security.config.SecurityProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
/**
 * 注入记录日志的注解
 */
@Import(LogAspect.class)
public class MonkeyzicloudConfig {
}
