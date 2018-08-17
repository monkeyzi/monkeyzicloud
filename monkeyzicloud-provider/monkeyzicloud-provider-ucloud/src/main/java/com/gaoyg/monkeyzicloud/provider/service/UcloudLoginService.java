package com.gaoyg.monkeyzicloud.provider.service;

import com.gaoyg.monkeyzicloud.provider.model.dto.LoginRespDto;

/**
 * @author: 高yg
 * @date: 2018/8/14 21:19
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudLoginService{
    /**
     * get menuVoList and LoginAuthDto after userLogin
     * @param applicationId
     * @return
     */
  LoginRespDto loginAfter(Long applicationId);
}
