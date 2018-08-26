package com.gaoyg.monkeyzicloud.provider.service.impl;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseService;
import com.gaoyg.monkeyzicloud.commom.core.util.RequestUtils;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.dto.UserTokenDto;
import com.gaoyg.monkeyzicloud.provider.enums.UcloudUserTokenStatusEnum;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudUserTokenMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudUser;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudUserToken;
import com.gaoyg.monkeyzicloud.provider.model.dto.token.TokenQueryDto;
import com.gaoyg.monkeyzicloud.provider.service.UcloudTokenService;
import com.gaoyg.monkeyzicloud.provider.service.UcloudUserService;
import com.gaoyg.monkeyzicloud.security.config.OAuth2ClientProperties;
import com.gaoyg.monkeyzicloud.security.config.SecurityProperties;
import com.gaoyg.monkeyzicloud.util.pubutils.RedisKeyUtil;
import com.github.pagehelper.PageInfo;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author: 高yg
 * @date: 2018/8/5 09:05
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Service
@Transactional(rollbackFor=Exception.class)
@Slf4j
public class UcloudUserTokenServiceImpl extends BaseService<UcloudUserToken> implements UcloudTokenService {
   @Autowired
   private UcloudUserService ucloudUserService;
   @Autowired
   private SecurityProperties securityProperties;
   @Autowired
   private UcloudUserTokenMapper ucloudUserTokenMapper;
   @Autowired
   private RedisTemplate<String, Object> redisTemplate;
    @Override
    public void saveUserToken(String accessToken, String refreshToken,String tokenType, LoginAuthDto loginAuthDto, HttpServletRequest request) {
        Long userId=loginAuthDto.getUserId();
        UcloudUser ucloudUser=ucloudUserService.selectByKey(userId);
        //获取浏览器信息
        final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("user-Agent"));
        //获取客户端操作系统
        final String os = userAgent.getOperatingSystem().getName();
        //获取客户端浏览器
        final String browser = userAgent.getBrowser().getName();
        final String remoteAddr = RequestUtils.getRemoteAddr(request);
        // 根据IP获取位置信息
        final String remoteLocation = "";
        //将信息保存到数据库中
        UcloudUserToken userToken=new UcloudUserToken();
        //获取到token的有效期
        OAuth2ClientProperties[] clients=securityProperties.getOauth2().getClients();
        int accessTokenValidateSeconds = clients[0].getAccessTokenValidateSeconds();
        int refreshTokenValiditySeconds = clients[0].getRefreshTokenValiditySeconds();
        userToken.setOs(os);
        userToken.setLoginIp(remoteAddr);
        userToken.setLoginLocation(remoteLocation);
        userToken.setLoginTime(ucloudUser.getLastLoginTime());
        userToken.setLoginName(loginAuthDto.getLoginName());
        userToken.setUserName(loginAuthDto.getUserName());
        userToken.setAccessToken(accessToken);
        userToken.setRefreshToken(refreshToken);
        userToken.setAccessTokenValidity(accessTokenValidateSeconds);
        userToken.setRefreshTokenValidity(refreshTokenValiditySeconds);
        userToken.setUserId(loginAuthDto.getUserId());
        userToken.setGroupId(loginAuthDto.getGroupId());
        userToken.setGroupName(loginAuthDto.getGroupName());
        userToken.setStatus(UcloudUserTokenStatusEnum.ON_LINE.getStatus());
        userToken.setTokenType(tokenType);
        userToken.setUpdateInfo(loginAuthDto);
        ucloudUserTokenMapper.insertSelective(userToken);
        //将token保存在redis中
        updateRedisUserToken(accessToken,accessTokenValidateSeconds,userToken);
        log.info("token保存到redis success");
    }

    /**
     * token保存在redis中
     * @param accessToken
     * @param accessTokenValidateSeconds
     * @param userToken
     */
    private void updateRedisUserToken(String accessToken, int accessTokenValidateSeconds, UcloudUserToken userToken) {
        redisTemplate.opsForValue().set(RedisKeyUtil.getAccessTokenKey(accessToken), userToken, accessTokenValidateSeconds, TimeUnit.SECONDS);
    }
    @Override
    public UserTokenDto getUserToken(String accessToken) {
         UserTokenDto userTokenDto= (UserTokenDto) redisTemplate.opsForValue().get(RedisKeyUtil.getAccessTokenKey(accessToken));
         if (userTokenDto==null){
           UcloudUserToken userToken=new UcloudUserToken();
           userToken.setAccessToken(accessToken);
           userToken=ucloudUserTokenMapper.selectOne(userToken);
           userTokenDto=new ModelMapper().map(userToken,UserTokenDto.class);
         }
         return userTokenDto;
    }

    /**
     * 更新token
     * @param userTokenDto
     * @param loginAuthDto
     */
    @Override
    public void updateUcloudUserToken(UserTokenDto userTokenDto, LoginAuthDto loginAuthDto) {
        UcloudUserToken userToken=new ModelMapper().map(userTokenDto,UcloudUserToken.class);
        userToken.setUpdateInfo(loginAuthDto);
        ucloudUserTokenMapper.updateByPrimaryKeySelective(userToken);
        OAuth2ClientProperties[] clients=securityProperties.getOauth2().getClients();
        int accessTokenValidateSeconds = clients[0].getAccessTokenValidateSeconds();
        updateRedisUserToken(userToken.getAccessToken(),accessTokenValidateSeconds,userToken);
    }

    @Override
    public PageInfo queryTokenWithPage(TokenQueryDto queryDto) {
        return null;
    }

    @Override
    public String refreshToken(String accessToken, String refreshToken, HttpServletRequest request) {
        return null;
    }

    @Override
    public int batchUpdateTokenOffLine() {
        return 0;
    }
}
