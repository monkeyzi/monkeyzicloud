package com.gaoyg.monkeyzicloud.provider.service.impl;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseService;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudGroupUserMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudGroupUser;
import com.gaoyg.monkeyzicloud.provider.service.UcloudGroupUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: 高yg
 * @date: 2018/8/23 21:45
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Service
@Slf4j
public class UcloudGroupUserServiceImpl extends BaseService<UcloudGroupUser> implements UcloudGroupUserService {

    @Autowired
    private UcloudGroupUserMapper ucloudGroupUserMapper;
    @Override
    public int updateByUserId(UcloudGroupUser groupUser) {
        return ucloudGroupUserMapper.updateByUserId(groupUser);
    }

    @Override
    public UcloudGroupUser queryByUserId(Long userId) {
        return ucloudGroupUserMapper.getByUserId(userId);
    }
}
