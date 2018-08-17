package com.gaoyg.monkeyzicloud.provider.mapper;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.MyMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudMenu;
import com.gaoyg.monkeyzicloud.provider.vo.MenuVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author: 高yg
 * @date: 2018/8/14 22:23
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Mapper
@Component
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