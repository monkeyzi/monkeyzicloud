package com.gaoyg.monkeyzicloud.provider.service.impl;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseService;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.provider.exception.UcloudBizException;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudActionMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudAction;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudMenu;
import com.gaoyg.monkeyzicloud.provider.service.UcloudActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/9/2 20:33
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UcloudActionServiceImpl extends BaseService<UcloudAction> implements UcloudActionService {
    @Autowired
    private UcloudActionMapper ucloudActionMapper;

    @Override
    public List<UcloudAction> listActionList(List<UcloudMenu> ucloudMenus) {
        return ucloudActionMapper.listActionList(ucloudMenus);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Long> getCheckedActionList(Long roleId) {
        if (roleId==null){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012001);
        }
        return ucloudActionMapper.getCheckedActionList(roleId);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Long> getCheckedMenuList(Long roleId) {
        if (roleId==null){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012001);
        }
        return ucloudActionMapper.getCheckedMenuList(roleId);
    }
}
