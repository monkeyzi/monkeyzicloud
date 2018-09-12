package com.gaoyg.monkeyzicloud.provider.mapper;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.MyMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRole;
import com.gaoyg.monkeyzicloud.provider.model.dto.UserRole.BindUserDto;
import com.gaoyg.monkeyzicloud.provider.model.vo.RoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/21 20:56
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Component
@Mapper
public interface UcloudRoleMapper extends MyMapper<UcloudRole> {
    /**
     * find role by roleCode
     * @param roleCode
     * @return
     */
    UcloudRole findByRoleCode(String roleCode);

    /**
     * find role list with page
     * @param role
     * @return
     */
    List<RoleVo> queryRoleListWithPage(UcloudRole role);

    /**
     * find all roleInfo by userid
     * @param userId
     * @return
     */
    List<UcloudRole> selectAllRoleInfoByUserId(Long userId);

    /**
     * select role list
     * @param role
     * @return
     */
    List<UcloudRole> selectRoleList(UcloudRole role);

    /**
     * batch Delete By IdList
     * @param idList
     * @return
     */
    int batchDeleteByIdList(@Param("idList") List<Long> idList);

    List<BindUserDto> selectAllNeedBindUser(@Param("superManagerRoleId") Long superManagerRoleId,
                                            @Param("currentUserId") Long currentUserId);
}
