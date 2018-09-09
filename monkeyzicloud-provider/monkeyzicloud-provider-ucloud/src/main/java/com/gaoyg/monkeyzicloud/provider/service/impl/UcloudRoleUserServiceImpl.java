package com.gaoyg.monkeyzicloud.provider.service.impl;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseService;
import com.gaoyg.monkeyzicloud.constant.GlobalConstant;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.provider.exception.UcloudBizException;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudRoleUserMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRoleUser;
import com.gaoyg.monkeyzicloud.provider.service.UcloudRoleUserService;
import com.gaoyg.monkeyzicloud.util.pubutils.PublicUtil;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author: 高yg
 * @date: 2018/8/21 22:31
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UcloudRoleUserServiceImpl extends BaseService<UcloudRoleUser> implements UcloudRoleUserService {
   @Autowired
    private UcloudRoleUserMapper ucloudRoleUserMapper;

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public List<UcloudRoleUser> selectRoleUserList(Long roleId) {
        return ucloudRoleUserMapper.listByRoleId(roleId);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        Preconditions.checkArgument(roleId != null, ErrorCodeEnum.UCLOUD10012001.getMsg());
        Preconditions.checkArgument(!Objects.equals(roleId, GlobalConstant.Sys.SUER_MANAGE_ROLE_ID), "超级管理员角色不能删除");
        int result = ucloudRoleUserMapper.deleteByRoleId(roleId);
        if (result < 1) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012006, roleId);
        }
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public List<UcloudRoleUser> selectByRoleIdList(List<Long> roleIds) {
        if (PublicUtil.isEmpty(roleIds)) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012001);
        }
        return ucloudRoleUserMapper.listByRoleIdList(roleIds);
    }

    @Override
    public void deleteByRoleIdList(List<Long> roleIdList) {
        Preconditions.checkArgument(roleIdList != null, ErrorCodeEnum.UCLOUD10012001.getMsg());
        Preconditions.checkArgument(!roleIdList.contains(GlobalConstant.Sys.SUER_MANAGE_ROLE_ID), "超级管理员角色不能删除");
        int result=ucloudRoleUserMapper.deleteByRoleIdList(roleIdList);
        if (result < roleIdList.size()) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012007, Joiner.on(GlobalConstant.Symbol.COMMA).join(roleIdList));
        }
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public UcloudRoleUser getByUserIdAndRoleId(Long userId, Long roleId) {
        return ucloudRoleUserMapper.getByUserIdAndRoleId(userId,roleId);
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public List<UcloudRoleUser> listByUserId(Long userId) {
        if (userId==null){
           throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011011);
        }
        return ucloudRoleUserMapper.listByUserId(userId);
    }

    @Override
    public int deleteByUserId(Long userId) {
        if (userId==null){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011001,userId);
        }
        UcloudRoleUser roleUser=new UcloudRoleUser();
        roleUser.setUserId(userId);
        return ucloudRoleUserMapper.delete(roleUser);
    }

    @Override
    public int saveRoleUser(Long userId, Long roleId) {
        if (userId==null){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011001,userId);
        }
        if (roleId==null){
            throw  new UcloudBizException(ErrorCodeEnum.UCLOUD10012001,roleId);
        }
        UcloudRoleUser roleUser=new UcloudRoleUser();
        roleUser.setUserId(userId);
        roleUser.setRoleId(roleId);
        return ucloudRoleUserMapper.insertSelective(roleUser);
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public List<Long> listSuperUser(Long superManagerRoleId) {
        if (null==superManagerRoleId){
            throw  new UcloudBizException(ErrorCodeEnum.UCLOUD10012001);
        }
        return ucloudRoleUserMapper.listSuperUser(superManagerRoleId);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<UcloudRoleUser> listByRoleId(Long roleId) {
        if (null==roleId){
            throw  new UcloudBizException(ErrorCodeEnum.UCLOUD10012001);
        }
        return ucloudRoleUserMapper.listByRoleId(roleId);
    }

    @Override
    public void deleteExcludeSuperMng(Long roleId, Long superManagerRoleId) {
        if (roleId == null) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012001);
        }
        if (superManagerRoleId == null) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD100120012);
        }
        ucloudRoleUserMapper.deleteExcludeSuperMng(roleId, superManagerRoleId);
    }
}
