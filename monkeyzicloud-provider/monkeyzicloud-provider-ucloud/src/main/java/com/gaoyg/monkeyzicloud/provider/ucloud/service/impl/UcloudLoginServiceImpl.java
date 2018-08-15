package com.gaoyg.monkeyzicloud.provider.ucloud.service.impl;

import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.provider.ucloud.constant.UcloudConstant;
import com.gaoyg.monkeyzicloud.provider.ucloud.exception.UcloudBizException;
import com.gaoyg.monkeyzicloud.provider.ucloud.model.domain.UcloudUser;
import com.gaoyg.monkeyzicloud.provider.ucloud.model.dto.LoginRespDto;
import com.gaoyg.monkeyzicloud.provider.ucloud.security.SecurityUtils;
import com.gaoyg.monkeyzicloud.provider.ucloud.service.UcloudLoginService;
import com.gaoyg.monkeyzicloud.provider.ucloud.service.UcloudMenuService;
import com.gaoyg.monkeyzicloud.provider.ucloud.service.UcloudUserService;
import com.gaoyg.monkeyzicloud.provider.ucloud.vo.MenuVo;
import com.gaoyg.monkeyzicloud.util.pubutils.PublicUtil;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author: 高yg
 * @date: 2018/8/14 21:20
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UcloudLoginServiceImpl implements UcloudLoginService {
    @Resource
    private UcloudUserService ucloudUserService;
    @Resource
    private UcloudMenuService ucloudMenuService;

    /**
     * 用户登录后获取用户的菜单列表和信息
     * @param applicationId
     * @return
     */
    @Override
    public LoginRespDto loginAfter(Long applicationId) {
        LoginRespDto loginRespDto=new LoginRespDto();
        String loginName=SecurityUtils.getCurrentLoginName();
        if (StringUtils.isEmpty(loginName)) {
            log.error("登录超时，请重新登录，loginName={}", loginName);
            Preconditions.checkArgument(StringUtils.isNotBlank(loginName), "操作超时, 请重新登录");
        }
        UcloudUser ucloudUser=ucloudUserService.findByLoginName(loginName);
        if (ucloudUser==null){
            log.error("找不到用户信息,ucloudUser={}",ucloudUser);
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011002,loginName);
        }
        LoginAuthDto loginAuthDto=this.getLoginAuthDto(ucloudUser);
        List<MenuVo> menuVoList=ucloudMenuService.getMenuVoList(ucloudUser.getId(),applicationId);
        if (PublicUtil.isNotEmpty(menuVoList)&&UcloudConstant.MENU_ROOT.equals(menuVoList.get(0).getMenuCode())){
            menuVoList=menuVoList.get(0).getSubMenu();
        }
        loginRespDto.setLoginAuthDto(loginAuthDto);
        loginRespDto.setMenuList(menuVoList);
        return loginRespDto;
    }

    private LoginAuthDto getLoginAuthDto(UcloudUser ucloudUser){
        LoginAuthDto loginAuthDto=new LoginAuthDto();
        loginAuthDto.setLoginName(ucloudUser.getLoginName());
        loginAuthDto.setUserId(ucloudUser.getId());
        loginAuthDto.setUserName(ucloudUser.getUserName());
        return loginAuthDto;
    }
}
