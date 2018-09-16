package com.gaoyg.monkeyzicloud.provider.service.impl;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseService;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.provider.exception.UcloudBizException;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudRoleActionMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRoleAction;
import com.gaoyg.monkeyzicloud.provider.service.UcloudRoleActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author: 高yg
 * @date: 2018/8/21 23:56
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UcloudRoleActionServiceImpl extends BaseService<UcloudRoleAction>  implements UcloudRoleActionService {

    @Autowired
    private UcloudRoleActionMapper roleActionMapper;
    @Override
    public void deleteByRoleId(Long roleId) {
       if (roleId==null){
           throw  new UcloudBizException(ErrorCodeEnum.UCLOUD10012001);
       }
       UcloudRoleAction roleAction=new UcloudRoleAction();
       roleAction.setRoleId(roleId);
       roleActionMapper.delete(roleAction);
    }

    @Override
    public int deleteByRoleIdList(List<Long> roleIdList) {
        return roleActionMapper.deleteByRoleIdList(roleIdList);
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public List<UcloudRoleAction> listByRoleId(Long roleId) {
        if (roleId == null) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012001);
        }
        UcloudRoleAction roleAction = new UcloudRoleAction();
        roleAction.setRoleId(roleId);
        return roleActionMapper.select(roleAction);
    }

    @Override
    public void insert(Long roleId, Set<Long> actionIdList) {
        if (roleId == null) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012001);
        }
        UcloudRoleAction uacRoleAction = new UcloudRoleAction();
        uacRoleAction.setRoleId(roleId);
        for (Long actionId : actionIdList) {
            uacRoleAction.setActionId(actionId);
            roleActionMapper.insert(uacRoleAction);
        }
    }
}
