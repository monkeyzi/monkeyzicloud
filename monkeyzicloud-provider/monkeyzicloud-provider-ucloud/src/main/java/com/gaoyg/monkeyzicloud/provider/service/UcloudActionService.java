package com.gaoyg.monkeyzicloud.provider.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudAction;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudMenu;
import com.gaoyg.monkeyzicloud.provider.model.dto.action.ActionMainQueryDto;
import com.github.pagehelper.PageInfo;

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

    /**
     * 根据菜单Id查询权限列表
     * @param menuId
     * @return
     */
    List<UcloudAction> findActionListByMenuId(Long menuId);

    /**
     * 根据菜单Id删除权限
     * @param menuId
     * @return
     */
    int deleteByMenuId(Long menuId);

    /**
     * 分页查询权限
     * @param actionMainQueryDto
     * @return
     */
    PageInfo queryActionListWithPage(ActionMainQueryDto actionMainQueryDto);

    /**
     * 删除权限
     * @param actionId
     * @return
     */
    int deleteActionById(Long actionId);

    /**
     * 批量删除权限
     * @param deleteIdList
     */
    void batchDeleteByIdList(List<Long> deleteIdList);

    /**
     * 新增或者修改权限
     * @param action
     * @param loginAuthDto
     */
    void saveAction(UcloudAction action, LoginAuthDto loginAuthDto);
}
