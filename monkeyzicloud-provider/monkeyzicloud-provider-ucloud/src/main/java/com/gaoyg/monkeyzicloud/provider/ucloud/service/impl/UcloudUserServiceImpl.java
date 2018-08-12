package com.gaoyg.monkeyzicloud.provider.ucloud.service.impl;

import com.gaoyg.monkeyzicloud.commom.core.enums.LogTypeEnum;
import com.gaoyg.monkeyzicloud.commom.core.support.BaseService;
import com.gaoyg.monkeyzicloud.commom.core.util.RequestUtils;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.provider.ucloud.mapper.UcloudUserMapper;
import com.gaoyg.monkeyzicloud.provider.ucloud.model.domain.UcloudLog;
import com.gaoyg.monkeyzicloud.provider.ucloud.model.domain.UcloudUser;
import com.gaoyg.monkeyzicloud.provider.ucloud.service.UcloudLogService;
import com.gaoyg.monkeyzicloud.provider.ucloud.service.UcloudTokenService;
import com.gaoyg.monkeyzicloud.provider.ucloud.service.UcloudUserService;
import com.netflix.discovery.converters.Auto;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author: 高yg
 * @date: 2018/8/5 21:02
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UcloudUserServiceImpl extends BaseService<UcloudUser> implements UcloudUserService {

    @Resource
    private UcloudUserMapper ucloudUserMapper;
    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private UcloudTokenService ucloudTokenService;

    @Autowired
    private UcloudLogService ucloudLogService;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public UcloudUser findByLoginName(String loginName) {
        log.info("用户登录名为:{}",loginName);
        return ucloudUserMapper.findByLoginName(loginName);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public UcloudUser findUserInfoByUserId(Long userId) {
        return ucloudUserMapper.selectUserInfoByUserId(userId);
    }

    @Override
    public int updateUserInfo(UcloudUser ucloudUser) {
        log.info("更新用户的信息,uclouduser={}",ucloudUser);
        int updateResult=ucloudUserMapper.updateByPrimaryKeySelective(ucloudUser);
        if (updateResult<1){
            log.info("用户【 {} 】修改用户信息失败", ucloudUser.getId());
        }else {
            log.info("用户【 {} 】修改用户信息成功",ucloudUser.getId());
        }
        return updateResult;
    }

    /**
     * 处理用户的登录信息
     * @param token
     * @param principal
     * @param request
     */
    @Override
    public void handlerLoginData(OAuth2AccessToken token, User principal, HttpServletRequest request) {
        //获取浏览器信息
        final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        //获取客户端操作系统
        final String os = userAgent.getOperatingSystem().getName();
        //获取客户端浏览器
        final String browser = userAgent.getBrowser().getName();
        //获取Ip
        final String remoteAddr = RequestUtils.getRemoteAddr(request);
        //获取访问路径
        final String requestURI = request.getRequestURI();
        //根据ip获取位置信息 TODO
        final String remoteLocation = "";
        UcloudUser ucloudUser=new UcloudUser();
        UcloudUser user=ucloudUserMapper.findByLoginName(principal.getUsername());
        ucloudUser.setId(user.getId());
        ucloudUser.setLastLoginIp(remoteAddr);
        ucloudUser.setLastLoginLocation(remoteLocation);
        ucloudUser.setLastLoginTime(new Date());
        LoginAuthDto loginAuthDto=new LoginAuthDto(user.getId(),user.getLoginName(),user.getUserName());
        //token信息
        String access_token=token.getValue();
        String refresh_token=token.getRefreshToken().getValue();
        String tokenType=token.getTokenType();
        //记录用户token信息
        log.info("保存用户的token信息");
        ucloudTokenService.saveUserToken(access_token,refresh_token,tokenType,loginAuthDto,request);
        //更新用户的最后登录时间
        taskExecutor.execute(()->this.updateUserInfo(ucloudUser));
        //记录用户登录日志
        UcloudLog ulog=new UcloudLog();
        ulog.setGroupId(user.getGroupId());
        ulog.setGroupName(user.getGroupName());
        ulog.setBrowser(browser);
        ulog.setOs(os);
        ulog.setIp(remoteAddr);
        ulog.setLocation(remoteLocation);
        ulog.setRequestUrl(requestURI);
        ulog.setLogType(LogTypeEnum.LOGIN_LOG.getType());
        ulog.setLogName(LogTypeEnum.LOGIN_LOG.getName());
        //执行保存日志
        taskExecutor.execute(()->ucloudLogService.saveLog(ulog,loginAuthDto));
    }
}
