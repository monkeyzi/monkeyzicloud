package com.gaoyg.monkeyzicloud.provider.ucloud.mapper;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.MyMapper;
import com.gaoyg.monkeyzicloud.provider.ucloud.model.domain.UcloudMenu;
import com.gaoyg.monkeyzicloud.provider.ucloud.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author: 高yg
 * @date: 2018/8/14 22:23
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudMenuMapper extends MyMapper<UcloudMenu> {
    /**
     * 查询菜单列表
     * @param ucloudMenu
     * @return
     */
    List<UcloudMenu> selectMenuList(UcloudMenu ucloudMenu);

    /**
     * 查询某人的菜单列表
     * @param userId
     * @return
     */
    List<MenuVo> findMenuVoListByUserId(Long userId);

    /**
     * get listMenu by menuIds
     * @param menuIdList
     * @return
     */
    List<UcloudMenu> listMenu(@Param("menuIdList") Set<Long> menuIdList);
}
