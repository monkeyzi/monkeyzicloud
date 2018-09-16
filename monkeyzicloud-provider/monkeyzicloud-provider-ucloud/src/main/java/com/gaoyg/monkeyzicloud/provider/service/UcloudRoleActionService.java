package com.gaoyg.monkeyzicloud.provider.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRoleAction;

import java.util.List;
import java.util.Set;

/**
 * @author: 高yg
 * @date: 2018/8/21 23:56
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudRoleActionService extends IBaseService<UcloudRoleAction> {
    /**
     * delete by roleId
     * @param roleId
     */
    void deleteByRoleId(Long roleId);

    /**
     * delete by roleIds
     * @param roleIdList
     * @return
     */
    int deleteByRoleIdList(List<Long> roleIdList);

    /**
     * 根据角色Id查询该角色已经绑定的权限
     * @param roleId
     * @return
     */
    List<UcloudRoleAction> listByRoleId(Long roleId);

    /**
     * 角色绑定权限
     * @param roleId
     * @param actionIdList
     */
    void insert(Long roleId, Set<Long> actionIdList);
}
