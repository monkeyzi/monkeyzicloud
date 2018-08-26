package com.gaoyg.monkeyzicloud.provider.service.impl;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseService;
import com.gaoyg.monkeyzicloud.constant.GlobalConstant;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.provider.exception.UcloudBizException;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudRoleMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRole;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRoleUser;
import com.gaoyg.monkeyzicloud.provider.model.vo.RoleVo;
import com.gaoyg.monkeyzicloud.provider.service.UcloudRoleActionService;
import com.gaoyg.monkeyzicloud.provider.service.UcloudRoleMenuService;
import com.gaoyg.monkeyzicloud.provider.service.UcloudRoleService;
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
 * @date: 2018/8/21 21:12
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UcloudRoleServiceImpl extends BaseService<UcloudRole>  implements UcloudRoleService {

    @Autowired
    private UcloudRoleMapper ucloudRoleMapper;
    @Autowired
    private UcloudRoleUserService ucloudRoleUserService;
    @Autowired
    private UcloudRoleMenuService ucloudRoleMenuService;
    @Autowired
    private UcloudRoleActionService ucloudRoleActionService;


    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public List<RoleVo> queryRoleListWithPage(UcloudRole role) {
        return ucloudRoleMapper.queryRoleListWithPage(role);
    }

    @Override
    public int deleteRoleById(Long id) {
        if (null==id){
            throw new IllegalArgumentException(ErrorCodeEnum.UCLOUD10012001.getMsg());
        }
        //超级管理员不能删除
        if (Objects.equals(id,GlobalConstant.Sys.SUER_MANAGE_ROLE_ID)){
            throw  new UcloudBizException(ErrorCodeEnum.UCLOUD10012003);
        }
        //查询该角色有没有绑定用户
        List<UcloudRoleUser> listRoleUser=ucloudRoleUserService.selectRoleUserList(id);
        if (!listRoleUser.isEmpty()){
            //删除角色用户关联表
            ucloudRoleUserService.deleteByRoleId(id);
        }
        //删除角色权限表
        ucloudRoleActionService.deleteByRoleId(id);
        //删除角色菜单表
        ucloudRoleMenuService.deleteByRoleId(id);
        //删除角色表
        return ucloudRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void batchDeleteByIdList(List<Long> idList) {
        log.info("批量删除角色 idList={}",idList);
        Preconditions.checkArgument(PublicUtil.isNotEmpty(idList),"角色id不能为空");
        List<UcloudRoleUser> roleUserList=ucloudRoleUserService.selectByRoleIdList(idList);
        if (!roleUserList.isEmpty()){
            //删除角色用户关联
            ucloudRoleUserService.deleteByRoleIdList(idList);
        }
        //删除角色权限关联表
        ucloudRoleActionService.deleteByRoleIdList(idList);
        //删除角色菜单关联表
        ucloudRoleMenuService.deleteByRoleIdList(idList);
        //删除角色表
        int result=ucloudRoleMapper.batchDeleteByIdList(idList);
        if (result<idList.size()){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012006, Joiner.on(GlobalConstant.Symbol.COMMA).join(idList));
        }
    }

    @Override
    public int saveRole(UcloudRole role, LoginAuthDto loginAuthDto) {
        int result = 0;
        role.setUpdateInfo(loginAuthDto);
        if (role.isNew()) {
            ucloudRoleMapper.insertSelective(role);
        } else {
            result = ucloudRoleMapper.updateByPrimaryKeySelective(role);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public UcloudRole getRoleById(Long roleId) {
        return ucloudRoleMapper.selectByPrimaryKey(roleId);
    }
}
