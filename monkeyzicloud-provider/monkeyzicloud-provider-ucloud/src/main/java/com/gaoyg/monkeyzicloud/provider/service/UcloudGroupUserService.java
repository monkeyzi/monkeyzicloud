package com.gaoyg.monkeyzicloud.provider.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudGroupUser;

/**
 * @author: 高yg
 * @date: 2018/8/23 21:45
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudGroupUserService extends IBaseService<UcloudGroupUser> {

    int updateByUserId(UcloudGroupUser groupUser);

    UcloudGroupUser queryByUserId(Long userId);
}
