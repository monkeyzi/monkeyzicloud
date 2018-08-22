package com.gaoyg.monkeyzicloud.provider.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRoleAction;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/21 23:56
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudRoleActionService extends IBaseService<UcloudRoleAction> {
    /**
     * delete by roleId
     * @param roleId
     */
    void deleteByRoleId(Long roleId);

    /**
     * delete by roleIds
     * @param roleIdList
     * @return
     */
    int deleteByRoleIdList(List<Long> roleIdList);
}
