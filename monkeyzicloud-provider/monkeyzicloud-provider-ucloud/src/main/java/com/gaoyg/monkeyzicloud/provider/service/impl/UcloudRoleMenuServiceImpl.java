package com.gaoyg.monkeyzicloud.provider.service.impl;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseService;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.provider.exception.UcloudBizException;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudRoleMenuMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRoleMenu;
import com.gaoyg.monkeyzicloud.provider.service.UcloudRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/21 23:49
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UcloudRoleMenuServiceImpl extends BaseService<UcloudRoleMenu>  implements UcloudRoleMenuService {
    @Autowired
    private UcloudRoleMenuMapper ucloudRoleMenuMapper;

    @Override
    public void deleteByRoleId(Long roleId) {
        if (roleId == null) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012001);
        }
        UcloudRoleMenu roleMenu=new UcloudRoleMenu();
        roleMenu.setRoleId(roleId);
        ucloudRoleMenuMapper.delete(roleMenu);
    }

    @Override
    public void deleteByRoleIdList(List<Long> roleIdList) {
        ucloudRoleMenuMapper.deleteByRoleIdList(roleIdList);
    }
}
