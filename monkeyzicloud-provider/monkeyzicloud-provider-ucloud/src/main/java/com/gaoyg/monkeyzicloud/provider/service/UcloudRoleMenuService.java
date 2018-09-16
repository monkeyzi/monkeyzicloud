package com.gaoyg.monkeyzicloud.provider.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRoleMenu;

import java.util.List;
import java.util.Set;

/**
 * @author: 高yg
 * @date: 2018/8/21 23:48
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudRoleMenuService extends IBaseService<UcloudRoleMenu> {
    /**
     * delete by roleId
     * @param roleId
     */
    void deleteByRoleId(Long roleId);

    /**
     * delete by roleId list
     * @param roleIdList
     */
    void deleteByRoleIdList(List<Long> roleIdList);

    /**
     * 角色绑定菜单
     * @param roleId
     * @param menuIdList
     */
    void insert(Long roleId, Set<Long> menuIdList);

    /**
     * 查询角色绑定过的菜单
     * @param roleId
     * @return
     */

    List<UcloudRoleMenu> listByRoleId(Long roleId);
}
