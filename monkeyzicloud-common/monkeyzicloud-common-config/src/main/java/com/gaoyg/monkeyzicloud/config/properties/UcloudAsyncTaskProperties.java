package com.gaoyg.monkeyzicloud.config.properties;

import lombok.Data;

/**
 * @author: 高yg
 * @date: 2018/8/11 23:06
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
public class UcloudAsyncTaskProperties {
    private int corePoolSize = 50;

    private int maxPoolSize = 100;

    private int queueCapacity = 10000;

    private int keepAliveSeconds = 3000;

    private String threadNamePrefix = "monkeyzicloud-task-executor-";
}
