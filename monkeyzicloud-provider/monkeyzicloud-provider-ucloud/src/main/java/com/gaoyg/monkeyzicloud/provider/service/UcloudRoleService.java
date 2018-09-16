package com.gaoyg.monkeyzicloud.provider.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRole;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudUser;
import com.gaoyg.monkeyzicloud.provider.model.dto.UserRole.RoleBindUserDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.role.RoleBindActionDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.role.RoleBindMenuDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.role.RoleBindUserReqDto;
import com.gaoyg.monkeyzicloud.provider.model.vo.RoleVo;
import com.gaoyg.monkeyzicloud.provider.model.vo.role.BindAuthVo;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/21 21:09
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudRoleService extends IBaseService<UcloudRole> {
    /**
     * 分页查询角色信息
     * @param role
     * @return
     */
    List<RoleVo> queryRoleListWithPage(UcloudRole role);

    /**
     * delete role by Id
     * @param id
     * @return
     */
    int deleteRoleById(Long id);

    /**
     * batch delete role
     * @param idList
     */
    void batchDeleteByIdList(List<Long> idList);

    /**
     * saveRole
     * @param role
     * @param loginAuthDto
     * @return
     */
    int saveRole(UcloudRole role, LoginAuthDto loginAuthDto);

    /**
     * 根据角色Id获取角色信息.
     *
     * @param roleId the role id
     *
     * @return the role by id
     */
    UcloudRole getRoleById(Long roleId);

    /**
     * 查询角色绑定的权限信息
     * @param roleId
     * @return
     */
    BindAuthVo getActionTreeByRoleId(Long roleId);

    /**
     * 查询角色绑定的菜单信息
     * @param roleId
     * @return
     */
    BindAuthVo getMenuTreeByRoleId(Long roleId);

    /**
     * 角色绑定用户
     * @param roleBindUserReqDto
     * @param loginAuthDto
     */
    void bindUserRole(RoleBindUserReqDto roleBindUserReqDto, LoginAuthDto loginAuthDto);

    /**
     * 获取角色绑定用户数据
     * @param roleId
     * @param currentUserId
     * @return
     */
    RoleBindUserDto getRoleBindUserDto(Long roleId, Long currentUserId);

    /**
     * 角色绑定权限
     * @param grantAuthRole
     */
    void bindAction(RoleBindActionDto grantAuthRole);

    /**
     * 角色绑定菜单
     * @param roleBindMenuDto
     */
    void bindMenu(RoleBindMenuDto roleBindMenuDto);
}
