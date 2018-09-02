package com.gaoyg.monkeyzicloud.provider.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudAction;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudMenu;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/9/2 20:32
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudActionService  extends IBaseService<UcloudAction> {
    /**
     * 根据菜单ID List 查询权限列表.
     * @param ucloudMenus
     * @return
     */
    List<UcloudAction> listActionList(List<UcloudMenu> ucloudMenus);

    /**
     * 根据Id查询该角色已经绑定的菜单和权限的集合
     * @param roleId
     * @return
     */
    List<Long> getCheckedActionList(Long roleId);

    /**
     * 根据Id查询该角色已经绑定的菜单Id的集合
     * @param roleId
     * @return
     */
    List<Long> getCheckedMenuList(Long roleId);
}
