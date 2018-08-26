/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：AsyncTaskExecutorConfiguration.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.gaoyg.monkeyzicloud.commom.core.config;

import com.gaoyg.monkeyzicloud.config.properties.MonkeyziCloudProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * The class Async config.
 *
 */
@Configuration
@EnableAsync
@EnableScheduling
@EnableConfigurationProperties(MonkeyziCloudProperties.class)
public class MonkeyziAsyncTaskExecutorConfiguration implements AsyncConfigurer {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private MonkeyziCloudProperties monkeyziCloudProperties;

	@Override
	@Bean(name = "taskExecutor")
	public Executor getAsyncExecutor() {
		log.debug("Creating Async Task Executor");
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(monkeyziCloudProperties.getTask().getCorePoolSize());
		executor.setMaxPoolSize(monkeyziCloudProperties.getTask().getMaxPoolSize());
		executor.setQueueCapacity(monkeyziCloudProperties.getTask().getQueueCapacity());
		executor.setKeepAliveSeconds(monkeyziCloudProperties.getTask().getKeepAliveSeconds());
		executor.setThreadNamePrefix(monkeyziCloudProperties.getTask().getThreadNamePrefix());
		return new ExceptionHandlingAsyncTaskExecutor(executor);
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}
}
