package com.gaoyg.monkeyzicloud.provider.mapper;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.MyMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudGroup;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudGroupUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/23 21:32
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Component
@Mapper
public interface UcloudGroupUserMapper extends MyMapper<UcloudGroupUser> {
    /**
     * Query by user id uac group user.
     *
     * @param userId the user id
     *
     * @return the uac group user
     */
    UcloudGroupUser getByUserId(Long userId);

    /**
     * Update by user id int.
     *
     * @param ucloudGroupUser the uac group user
     *
     * @return the int
     */
    int updateByUserId(UcloudGroupUser ucloudGroupUser);

    /**
     * Select group list by user id list.
     *
     * @param userId the user id
     *
     * @return the list
     */
    List<UcloudGroup> selectGroupListByUserId(Long userId);

    /**
     * List by group id list.
     *
     * @param groupId the group id
     *
     * @return the list
     */
    List<UcloudGroupUser> listByGroupId(@Param("groupId") Long groupId);

    /**
     * Delete exclude super mng int.
     *
     * @param groupId            the group id
     * @param superManagerRoleId the super manager role id
     *
     * @return the int
     */
    int deleteExcludeSuperMng(@Param("currentGroupId") Long groupId, @Param("superManagerRoleId") Long superManagerRoleId);
}
