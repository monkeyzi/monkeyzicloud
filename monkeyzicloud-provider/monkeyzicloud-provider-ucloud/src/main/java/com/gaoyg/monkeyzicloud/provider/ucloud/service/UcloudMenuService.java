package com.gaoyg.monkeyzicloud.provider.ucloud.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.provider.ucloud.model.domain.UcloudMenu;
import com.gaoyg.monkeyzicloud.provider.ucloud.vo.MenuVo;

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
    List<MenuVo> getMenuVoList(Long userId,Long applicationId);

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
}
