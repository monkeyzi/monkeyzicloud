package com.gaoyg.monkeyzicloud.config.properties;

import lombok.Data;

/**
 * @author: 高yg
 * @date: 2018/7/21 00:23
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
public class SwaggerProperties {

    private  String TITLE="monkeyzicloud项目api接口文档";

    private  String DESCRIPTION="monkeyzicloud项目使用swagger2生成优雅的文档";

    private  String VERSION = "1.0";

    private  String LICENSE = "Apache License 2.0";

    private  String LICENSEURL = "http://www.apache.org/licenses/LICENSE-2.0";

    private  String CONTACTNAME = "monkeyzi";

    private  String CONTACTURL = "https://monkeyzi.github.io";

    private  String CONTACTEMAIL = "854152531@qq.com";
}
