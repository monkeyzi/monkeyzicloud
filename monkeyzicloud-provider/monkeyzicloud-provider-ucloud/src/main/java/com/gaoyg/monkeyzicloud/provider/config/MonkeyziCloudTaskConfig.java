package com.gaoyg.monkeyzicloud.provider.config;

/**
 * @author: 高yg
 * @date: 2018/8/11 23:28
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */

import com.gaoyg.monkeyzicloud.commom.core.config.MonkeyziAsyncTaskExecutorConfiguration;
import com.gaoyg.monkeyzicloud.commom.core.config.MonkeyziRedisConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MonkeyziAsyncTaskExecutorConfiguration.class})
@EnableConfigurationProperties(MonkeyziRedisConfiguration.class)
public class MonkeyziCloudTaskConfig {
}
