package com.gaoyg.monkeyzicloud.provider.service.impl;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseService;
import com.gaoyg.monkeyzicloud.constant.GlobalConstant;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.provider.exception.UcloudBizException;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudRoleMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.*;
import com.gaoyg.monkeyzicloud.provider.model.dto.role.RoleBindUserReqDto;
import com.gaoyg.monkeyzicloud.provider.model.vo.RoleVo;
import com.gaoyg.monkeyzicloud.provider.model.vo.role.BindAuthVo;
import com.gaoyg.monkeyzicloud.provider.service.*;
import com.gaoyg.monkeyzicloud.provider.utils.TreeUtil;
import com.gaoyg.monkeyzicloud.provider.vo.MenuVo;
import com.gaoyg.monkeyzicloud.util.pubutils.PublicUtil;
import com.gaoyg.monkeyzicloud.util.util.Collections3;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    private UcloudMenuService ucloudMenuService;
    @Autowired
    private UcloudActionService ucloudActionService;
    @Autowired
    private UcloudUserService ucloudUserService;


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

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public BindAuthVo getActionTreeByRoleId(Long roleId) {
        BindAuthVo bindAuthVo=new BindAuthVo();
        Preconditions.checkArgument(roleId!=null,ErrorCodeEnum.UCLOUD10012010.getMsg());
        UcloudRole ucloudRole=this.getRoleById(roleId);
        if (ucloudRole==null){
            log.error("找不到角色信息 roleId={}", roleId);
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012011);
        }
        List<UcloudMenu> menuList=ucloudMenuService.listMenuListByRoleId(roleId);
        if (PublicUtil.isEmpty(menuList)) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10013009);
        }
        //查询所有的权限信息
        List<UcloudAction> actionList=ucloudActionService.listActionList(menuList);
        // 合并菜单和按钮权限 递归生成树结构
        List<MenuVo> menuVoList=this.getAuthList(menuList,actionList);
        List<MenuVo> tree = TreeUtil.getChildMenuVos(menuVoList, 0L);
        //获取所有已经绑定的菜单和权限Id集合
        List<Long> authActionList=ucloudActionService.getCheckedActionList(roleId);
        bindAuthVo.setAuthTree(tree);
        bindAuthVo.setCheckedAuthList(authActionList);
        return bindAuthVo;
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public BindAuthVo getMenuTreeByRoleId(Long roleId) {
        BindAuthVo bindAuthVo=new BindAuthVo();
        Preconditions.checkArgument(roleId!=null,ErrorCodeEnum.UCLOUD10012010.getMsg());
        UcloudRole ucloudRole=this.getRoleById(roleId);
        if (ucloudRole==null){
            log.error("找不到角色信息 roleId={}", roleId);
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012011);
        }
        //查询所有的菜单
        List<UcloudMenu> allMenuList=ucloudMenuService.selectAll();
        // 合并菜单和按钮权限 递归生成树结构
        List<MenuVo> menuVoList = this.getAuthList(allMenuList, null);
        List<MenuVo> tree = TreeUtil.getChildMenuVos(menuVoList, 0L);
        //查询已经绑定的菜单信息
        List<Long>   authMenuList=ucloudActionService.getCheckedMenuList(roleId);
        bindAuthVo.setCheckedAuthList(authMenuList);
        bindAuthVo.setAuthTree(tree);
        return bindAuthVo;
    }


    @Override
    public void bindUserRole(RoleBindUserReqDto roleBindUserReqDto, LoginAuthDto loginAuthDto) {
        if (roleBindUserReqDto == null) {
            log.error("参数不能为空");
            throw new IllegalArgumentException("参数不能为空");
        }
        Long roleId = roleBindUserReqDto.getRoleId();
        Long loginUserId = loginAuthDto.getUserId();
        List<Long> userIdList = roleBindUserReqDto.getUserIdList();
        if (null==roleId){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012001);
        }
        UcloudRole role=this.getRoleById(roleId);
        if (role == null) {
            log.error("找不到角色信息 roleId={}", roleId);
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10012011, roleId);
        }
        if (PublicUtil.isNotEmpty(userIdList)&&userIdList.contains(loginUserId)){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011023);
        }
        //查询超级管理员用户Id集合
        List<Long> superUserList=ucloudRoleUserService.listSuperUser(GlobalConstant.Sys.SUER_MANAGE_ROLE_ID);
        //交集
        List<Long> unionList = Collections3.intersection(userIdList, superUserList);
        if (PublicUtil.isNotEmpty(userIdList) && PublicUtil.isNotEmpty(unionList)) {
            log.error("不能操作超级管理员用户 超级用户={}", unionList);
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011023);
        }
        //先取消对该角色的用户绑定，(不包含超级管理员)
        List<UcloudRoleUser> userRoles = ucloudRoleUserService.listByRoleId(roleId);
        if (PublicUtil.isNotEmpty(userRoles)){
           ucloudRoleUserService.deleteExcludeSuperMng(roleId,GlobalConstant.Sys.SUER_MANAGE_ROLE_ID);
        }
        if (PublicUtil.isEmpty(userIdList)) {
            // 取消该角色的所有用户的绑定
            log.info("取消绑定所有非超级管理员用户成功");
            return;
        }
        userIdList.stream().forEach(a->{
            //先通过用户的Id查询用户
            UcloudUser ucloudUser=ucloudUserService.queryUserById(a);
            if (PublicUtil.isEmpty(ucloudUser)) {
                log.error("找不到绑定的用户 userId={}", a);
                throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011024, a);
            }
            //添加关系
            ucloudRoleUserService.saveRoleUser(a,roleId);
        });
    }


    private List<MenuVo> getAuthList(List<UcloudMenu> menuList, List<UcloudAction> actionList) {
        List<MenuVo> menuVoList= Lists.newArrayList();
        MenuVo menuVo;
        for (UcloudMenu vo:menuList){
            menuVo=new MenuVo();
            BeanUtils.copyProperties(vo,menuVo);
            menuVo.setRemark("menu");
            menuVoList.add(menuVo);
        }
        if (PublicUtil.isNotEmpty(actionList)){
            for (UcloudAction ucloudAction : actionList) {
                menuVo = new MenuVo();
                menuVo.setId(ucloudAction.getId());
                menuVo.setMenuName(ucloudAction.getActionName());
                menuVo.setMenuCode(ucloudAction.getActionCode());
                menuVo.setPid(ucloudAction.getMenuId());
                menuVo.setUrl(ucloudAction.getUrl());
                menuVo.setRemark("action");
                menuVoList.add(menuVo);
            }
        }
        return menuVoList;
    }
}
