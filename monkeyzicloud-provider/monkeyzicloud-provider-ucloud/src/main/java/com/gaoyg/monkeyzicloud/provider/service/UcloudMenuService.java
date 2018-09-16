package com.gaoyg.monkeyzicloud.provider.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudMenu;
import com.gaoyg.monkeyzicloud.provider.model.dto.menu.UcloudMenuStatusDto;
import com.gaoyg.monkeyzicloud.provider.model.vo.menu.ViewMenuVo;
import com.gaoyg.monkeyzicloud.provider.vo.MenuVo;

import java.util.List;
import java.util.Set;

/**
 * @author: 高yg
 * @date: 2018/8/14 22:02
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudMenuService extends IBaseService<UcloudMenu> {
    /**
     * getMenuVo list by userId and applicationId
     * @param userId
     * @param applicationId
     * @return
     */
    List<MenuVo> getMenuVoList(Long userId, Long applicationId);

    /**
     * get menuList by ids
     * @param ids
     * @return
     */
    List<UcloudMenu> getMenuList(Set<Long> ids);

    /**
     * query all menuList that enabled
     * @param ucloudMenu
     * @return
     */
    List<UcloudMenu> selectMenuList(UcloudMenu ucloudMenu);

    /**
     * 根据角色Id查询角色绑定的菜单信息
     * @param roleId
     * @return
     */
    List<UcloudMenu> listMenuListByRoleId(Long roleId);

    /**
     * 编辑菜单获取菜单的详情
     * @param id
     * @return
     */
    ViewMenuVo getViewVoById(Long id);

    /**
     * 修改菜单的状态
     * @param ucloudMenuStatusDto
     * @param loginAuthDto
     */
    void updateUcloudMenuStatus(UcloudMenuStatusDto ucloudMenuStatusDto, LoginAuthDto loginAuthDto);

    /**
     * 根据菜单的Id获取所有的子菜单
     * @param id
     * @param type
     * @return
     */
    List<UcloudMenu> getAllChildMenuByMenuId(Long id, String type);

    /**
     * 禁用菜单
     * @param menuList
     * @param loginAuthDto
     * @return
     */
    int disableMenuList(List<UcloudMenu> menuList, LoginAuthDto loginAuthDto);

    /**
     * 获取所有的父菜单Id
     * @param id
     * @return
     */
    List<UcloudMenu> getAllParentMenuByMenuId(Long id);

    /**
     * 启用菜单
     * @param menuList
     * @param loginAuthDto
     * @return
     */
    int enableMenuList(List<UcloudMenu> menuList, LoginAuthDto loginAuthDto);

    /**
     * 新增/修改菜单
     * @param ucloudMenu
     * @param loginAuthDto
     * @return
     */
    int saveUcloudMenu(UcloudMenu ucloudMenu, LoginAuthDto loginAuthDto);

    /**
     * 判断菜单是否有子菜单.
     *
     * @param menuId the menu id
     *
     * @return the boolean
     */
    boolean checkMenuHasChildMenu(Long menuId);

    /**
     * 删除菜单
     * @param id
     * @param loginAuthDto
     * @return
     */
    int deleteMenuByMenuId(Long id, LoginAuthDto loginAuthDto);

}
