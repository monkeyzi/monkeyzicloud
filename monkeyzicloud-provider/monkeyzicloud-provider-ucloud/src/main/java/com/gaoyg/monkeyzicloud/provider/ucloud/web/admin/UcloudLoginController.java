package com.gaoyg.monkeyzicloud.provider.ucloud.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.provider.ucloud.model.dto.LoginRespDto;
import com.gaoyg.monkeyzicloud.util.response.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 高yg
 * @date: 2018/7/30 22:57
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@RestController
@RequestMapping(value = "/user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "web-UcloudLoginController",description = "用户登陆管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudLoginController extends BaseController {

    @RequestMapping("/home/getAuthDataList")
    @ApiOperation(value = "登录后获取用户菜单列表和信息",httpMethod = "POST")
    public R<LoginRespDto> getAuthDatalist(){
        LoginAuthDto loginAuthDto=getLoginAuthDto();
        LoginRespDto loginRespDto=new LoginRespDto();
        return  R.ok("成功",loginRespDto);
    }
}
