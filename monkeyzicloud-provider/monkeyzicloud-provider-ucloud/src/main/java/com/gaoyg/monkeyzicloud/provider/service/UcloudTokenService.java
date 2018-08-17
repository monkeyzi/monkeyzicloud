package com.gaoyg.monkeyzicloud.provider.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.dto.UserTokenDto;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudUserToken;
import com.gaoyg.monkeyzicloud.provider.model.dto.token.TokenQueryDto;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 高yg
 * @date: 2018/8/5 08:12
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:token管理
 */
public interface UcloudTokenService extends IBaseService<UcloudUserToken> {

    /**
     * 保存token
     * @param accessToken
     * @param refreshToken
     * @param loginAuthDto
     * @param request
     */
    void saveUserToken(String accessToken, String refreshToken,String tokenType, LoginAuthDto loginAuthDto, HttpServletRequest request);

    /**
     * 获取token
     * @param accessToken
     * @return
     */
    UserTokenDto getUserToken(String accessToken);

    /**
     * 更新token
     * @param userTokenDto
     * @param loginAuthDto
     */
    void updateUcloudUserToken(UserTokenDto userTokenDto,LoginAuthDto loginAuthDto);

    /**
     * 分页查询用户的token
     * @param queryDto
     * @return
     */
    PageInfo queryTokenWithPage(TokenQueryDto queryDto);

    /**
     * 刷新token
     * @param accessToken
     * @param refreshToken
     * @param request
     * @return
     */
    String refreshToken(String accessToken, String refreshToken, HttpServletRequest request);

    /**
     * 批量更新token的离线状态
     * @return
     */
    int batchUpdateTokenOffLine();
}
