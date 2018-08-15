package com.gaoyg.monkeyzicloud.commom.core.support;

import com.gaoyg.monkeyzicloud.constant.GlobalConstant;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.exception.BusinessException;
import com.gaoyg.monkeyzicloud.util.pubutils.PublicUtil;
import com.gaoyg.monkeyzicloud.util.pubutils.ThreadLocalMap;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 高yg
 * @date: 2018/7/31 21:02
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
public class BaseController {
    /**
     * 获取当前的登录人的信息
     * @return
     */
  public LoginAuthDto getLoginAuthDto(){
     LoginAuthDto loginAuthDto= (LoginAuthDto) ThreadLocalMap.get(GlobalConstant.Sys.TOKEN_AUTH_USER);
     log.info("当前的登录人信息，{}",loginAuthDto);
     if (PublicUtil.isEmpty(loginAuthDto)){
         throw  new BusinessException(ErrorCodeEnum.UCLOUD00001038);
     }
     return loginAuthDto;
  }
}
