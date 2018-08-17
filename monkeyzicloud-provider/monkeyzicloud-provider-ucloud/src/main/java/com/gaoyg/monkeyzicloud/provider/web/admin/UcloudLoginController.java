package com.gaoyg.monkeyzicloud.provider.web.admin;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseController;
import com.gaoyg.monkeyzicloud.commom.core.util.RequestUtils;
import com.gaoyg.monkeyzicloud.dto.UserTokenDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.LoginRespDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudLoginService;
import com.gaoyg.monkeyzicloud.provider.service.UcloudTokenService;
import com.gaoyg.monkeyzicloud.provider.enums.UcloudUserTokenStatusEnum;
import com.gaoyg.monkeyzicloud.util.response.R;
import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: 高yg
 * @date: 2018/7/30 22:57
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:用户的登录管理
 */
@Slf4j
@RestController
@RequestMapping(value = "",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "web-UcloudLoginController",description = "用户登陆管理", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UcloudLoginController extends BaseController {

    @Autowired
    private UcloudLoginService ucloudLoginService;
    @Autowired
    private UcloudTokenService ucloudTokenService;

    @Resource
    private ClientDetailsService clientDetailsService;

    private static final String BEARER_TOKEN_TYPE = "Basic ";
    /**
     * 登录成功后获取用户的菜单列表和个人基本信息
     * @return
     */
    @PostMapping("/user/getAuthDataList/{applicationId}")
    @ApiOperation(value = "登录后获取用户菜单列表和信息",httpMethod = "POST")
    public R<LoginRespDto> getAuthDatalist(@ApiParam(name="applicationId",value = "应用的编号") @PathVariable Long applicationId){
        log.info("用户登录成功获取菜单和信息，applicationId={}",applicationId);
        //执行逻辑
        LoginRespDto loginRespDto=ucloudLoginService.loginAfter(applicationId);
        return  R.ok("成功",loginRespDto);
    }

    /**
     * 用户的退出操作
     * @param accessToken
     * @return
     */
    @PostMapping(value = "/user/logout")
    @ApiOperation(value = "退出",httpMethod = "POST")
    public R logout(String accessToken){
        if (StringUtils.isNotEmpty(accessToken)){
            //更新用户的在线状态
            UserTokenDto userTokenDto=ucloudTokenService.getUserToken(accessToken);
            userTokenDto.setStatus(UcloudUserTokenStatusEnum.OFF_LINE.getStatus());
            ucloudTokenService.updateUcloudUserToken(userTokenDto,getLoginAuthDto());
        }
        return R.ok();
    }

    /**
     * 刷新token
     * @param request
     * @param refreshToken
     * @param accessToken
     * @return
     */
    @GetMapping(value="/auth/user/refreshToken")
    @ApiOperation(value = "刷新token",httpMethod = "POST")

    public R refreshToken(HttpServletRequest request, @RequestParam(value = "refreshToken") String refreshToken,
                          @RequestParam(value = "accessToken") String accessToken){
        String  token;
        try {
            Preconditions.checkArgument(StringUtils.isNotEmpty(accessToken), "accessToken is null");
            Preconditions.checkArgument(StringUtils.isNotEmpty(refreshToken), "refreshToken is null");
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (header == null || !header.startsWith(BEARER_TOKEN_TYPE)) {
                throw new UnapprovedClientAuthenticationException("请求头中无client信息");
            }
            String[] tokens=RequestUtils.extractAndDecodeHeader(header);
            assert tokens.length == 2;

            String clientId = tokens[0];
            String clientSecret = tokens[1];
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

            if (clientDetails == null) {
                throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
            } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
                throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
            }
            token=ucloudTokenService.refreshToken(accessToken,refreshToken,request);
        }catch (Exception e){
            log.error("refreshToken={}", e.getMessage(), e);
            return R.error();
        }
        return R.ok("Ok",token);
    }
}
