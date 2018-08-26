package com.gaoyg.monkeyzicloud.provider.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRole;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudUser;
import com.gaoyg.monkeyzicloud.provider.model.vo.RoleVo;

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
}
