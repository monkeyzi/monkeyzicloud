package com.gaoyg.monkeyzicloud.provider.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRoleUser;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/21 22:30
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudRoleUserService extends IBaseService<UcloudRoleUser> {

    List<UcloudRoleUser> selectRoleUserList(Long roleId);

    void deleteByRoleId(Long roleId);

    List<UcloudRoleUser> selectByRoleIdList(List<Long> roleIds);

    void deleteByRoleIdList(List<Long> roleIdList);

    UcloudRoleUser getByUserIdAndRoleId(Long userId, Long roleId);
}
