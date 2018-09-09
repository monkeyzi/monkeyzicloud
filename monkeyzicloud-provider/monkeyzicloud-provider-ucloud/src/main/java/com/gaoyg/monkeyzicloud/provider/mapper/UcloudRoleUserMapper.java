package com.gaoyg.monkeyzicloud.provider.mapper;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.MyMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRoleUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/21 22:23
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Mapper
@Component
public interface UcloudRoleUserMapper extends MyMapper<UcloudRoleUser> {
    /**
     * 查询该角色绑定的数据
     * @param roleId
     * @return
     */
    List<UcloudRoleUser> listByRoleId(Long roleId);

    /**
     * delete by roleId
     * @param roleId
     * @return
     */
    int deleteByRoleId(@Param("roleId") Long roleId);

    /**
     * select by roleIds list
     * @param idList
     * @return
     */
    List<UcloudRoleUser> listByRoleIdList(@Param("roleIds") List<Long> idList);

    /**
     * delete by roleIdsList
     * @param roleIdList
     * @return
     */
    int deleteByRoleIdList(@Param("roleIdList") List<Long> roleIdList);

    /**
     * select by roleId and  userId
     * @param userId
     * @param roleId
     * @return
     */
    UcloudRoleUser getByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * select list by UserId
     * @param userId
     * @return
     */
    List<UcloudRoleUser> listByUserId(@Param("userId") Long userId);

    /**
     * 查询超级管理员角色绑定的用户Id
     * @param roleId
     * @return
     */
    List<Long> listSuperUser(@Param("roleId") Long roleId);

    /**
     * 删除该角色绑定的除了超管的用户
     * @param currentRoleId
     * @param superRoleId
     * @return
     */
    int deleteExcludeSuperMng(@Param("currentRoleId") Long currentRoleId, @Param("superRoleId") Long superRoleId);

}
