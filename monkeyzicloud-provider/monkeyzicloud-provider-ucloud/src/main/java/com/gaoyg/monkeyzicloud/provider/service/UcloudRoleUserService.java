package com.gaoyg.monkeyzicloud.provider.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRoleUser;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/21 22:30
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudRoleUserService extends IBaseService<UcloudRoleUser> {
    /**
     * 根据角色Id查询角色绑定
     * @param roleId
     * @return
     */
    List<UcloudRoleUser> selectRoleUserList(Long roleId);

    /**
     * 删除角色
     * @param roleId
     */
    void deleteByRoleId(Long roleId);

    /**
     * 批量查询角色
     * @param roleIds
     * @return
     */

    List<UcloudRoleUser> selectByRoleIdList(List<Long> roleIds);

    /**
     * 批量删除角色
     * @param roleIdList
     */
    void deleteByRoleIdList(List<Long> roleIdList);

    /**
     * 通过用户编号个角色编号查询角色和用户关系
     * @param userId
     * @param roleId
     * @return
     */
    UcloudRoleUser getByUserIdAndRoleId(Long userId, Long roleId);

    /**
     * 通过用户查询该用户的角色
     * @param userId
     * @return
     */
    List<UcloudRoleUser> listByUserId(Long userId);

    /**
     * 通过用户Id删除用户角色关系
     * @param userId
     * @return
     */
    int deleteByUserId(Long userId);

    /**
     * 保存用户角色中间表信息.
     *
     * @param userId the user id
     * @param roleId the role id
     *
     * @return the int
     */
    int saveRoleUser(Long userId, Long roleId);

    /**
     * 查询超级管理员的角色关系Id列表
     * @param superManagerRoleId
     * @return
     */
    List<Long> listSuperUser(Long superManagerRoleId);

    /**
     * 根据角色查询该角色下绑定的用户
     * @param roleId
     * @return
     */
    List<UcloudRoleUser> listByRoleId(Long roleId);

    /**
     * 删除该角色下绑定的用户，除去超管
     * @param roleId
     * @param superManagerRoleId
     */
    void deleteExcludeSuperMng(Long roleId, Long superManagerRoleId);
}
