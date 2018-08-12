package com.gaoyg.monkeyzicloud.config.properties;

import com.gaoyg.monkeyzicloud.constant.GlobalConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author: 高yg
 * @date: 2018/7/21 00:16
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ConfigurationProperties(prefix =GlobalConstant.MONKEYZI_PREFIX)
public class MonkeyziCloudProperties {
    private SwaggerProperties swaggerProperties=new SwaggerProperties();
    private UcloudAsyncTaskProperties task=new UcloudAsyncTaskProperties();
}
