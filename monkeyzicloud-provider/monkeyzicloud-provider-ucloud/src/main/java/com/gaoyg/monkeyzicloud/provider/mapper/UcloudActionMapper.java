package com.gaoyg.monkeyzicloud.provider.mapper;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.MyMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudAction;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudMenu;
import com.gaoyg.monkeyzicloud.provider.model.vo.action.ActionVo;
import com.gaoyg.monkeyzicloud.provider.vo.MenuVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/9/2 20:35
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Mapper
@Component
public interface UcloudActionMapper extends MyMapper<UcloudAction> {
    /**
     * 根据userId 查询权限编码列表
     * @param userId
     * @return
     */
    List<String> findActionCodeListByUserId(Long userId);

    /**
     * 批量删除权限
     * @param deleteIdList
     * @return
     */
    int batchDeleteByIdList(@Param("idList") List<Long> deleteIdList);

    /**
     * 分页查询所有的权限
     * @param ucloudAction
     * @return
     */
    List<ActionVo> queryActionListWithPage(UcloudAction ucloudAction);

    /**
     * 根据菜单Id删除权限
     * @param id
     * @return
     */
    int deleteByMenuId(@Param("menuId") Long id);

    /**
     * 查询该角色已经拥有的权限id列表
     * @param roleId
     * @return
     */
    List<Long> getCheckedActionList(@Param("roleId") Long roleId);

    /**
     * 查询某个用户下的菜单权限
     * @param userId
     * @return
     */
    List<MenuVo> getOwnAuthList(@Param("userId") Long userId);

    /**
     * 查询该角色的菜单列表
     * @param roleId
     * @return
     */
    List<Long> getCheckedMenuList(@Param("roleId") Long roleId);


    /**
     * 查询某个用户所有的权限
     * @param userId
     * @return
     */
    List<UcloudAction> getOwnUacActionListByUserId(Long userId);

    /**
     *根据角色ID查询权限列表.
     * @param roleId
     * @return
     */
    List<UcloudAction> listActionListByRoleId(@Param("roleId") Long roleId);

    /**
     *  批量查询权限列表
     * @param menuList
     * @return
     */
    List<UcloudAction> listActionList(@Param("menuList") List<UcloudMenu> menuList);

    /**
     * 根据菜单查询权限列表
     * @param menuId
     * @return
     */
    List<UcloudAction> findActionListByMenuId(@Param("menuId")Long menuId);
}
