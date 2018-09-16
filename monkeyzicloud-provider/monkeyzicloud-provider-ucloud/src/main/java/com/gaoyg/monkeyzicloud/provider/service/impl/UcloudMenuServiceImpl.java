package com.gaoyg.monkeyzicloud.provider.service.impl;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseService;
import com.gaoyg.monkeyzicloud.constant.GlobalConstant;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.provider.enums.UcloudMenuStatusEnum;
import com.gaoyg.monkeyzicloud.provider.exception.UcloudBizException;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudMenuMapper;
import com.gaoyg.monkeyzicloud.provider.model.constant.MenuConstant;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudAction;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudMenu;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRoleMenu;
import com.gaoyg.monkeyzicloud.provider.model.dto.menu.UcloudMenuStatusDto;
import com.gaoyg.monkeyzicloud.provider.model.vo.menu.ViewMenuVo;
import com.gaoyg.monkeyzicloud.provider.service.UcloudActionService;
import com.gaoyg.monkeyzicloud.provider.service.UcloudMenuService;
import com.gaoyg.monkeyzicloud.provider.service.UcloudRoleActionService;
import com.gaoyg.monkeyzicloud.provider.service.UcloudRoleMenuService;
import com.gaoyg.monkeyzicloud.provider.utils.TreeUtil;
import com.gaoyg.monkeyzicloud.provider.vo.MenuVo;
import com.gaoyg.monkeyzicloud.util.pubutils.PublicUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum.UCLOUD10014003;

/**
 * @author: 高yg
 * @date: 2018/8/14 22:05
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UcloudMenuServiceImpl extends BaseService<UcloudMenu> implements UcloudMenuService {

    @Resource
    private UcloudMenuMapper ucloudMenuMapper;
    @Resource
    private UcloudRoleMenuService ucloudRoleMenuService;
    @Resource
    private UcloudActionService ucloudActionService;

    @Override
    public List<MenuVo> getMenuVoList(Long userId, Long applicationId) {
       List<MenuVo> menuVoList=Lists.newArrayList();
       List<UcloudMenu> menuList;
       Set<UcloudMenu> menuSet=Sets.newHashSet();
       //超级管理员,查询所有
       if (userId.equals(GlobalConstant.Sys.SUPER_MANAGE_USER_ID)){
           UcloudMenu ucloudMenuQuery=new UcloudMenu();
           ucloudMenuQuery.setApplicationId(applicationId);
           ucloudMenuQuery.setStatus(UcloudMenuStatusEnum.ENABLE.getType());
           ucloudMenuQuery.setOrderBy("level asc,number asc");
           menuList=ucloudMenuMapper.selectMenuList(ucloudMenuQuery);
       }else {
        //查询用户拥有的而菜单列表
           menuVoList=ucloudMenuMapper.findMenuVoListByUserId(userId);
           if (PublicUtil.isEmpty(menuVoList)){
               return null;
           }
           Set<Long> ids=menuVoList.stream().map(MenuVo::getId).collect(Collectors.toSet());
           List<UcloudMenu> ownMenuList=this.getMenuList(ids);
           //查询出所有的菜单信息
           UcloudMenu ucloudMenu=new UcloudMenu();
           ucloudMenu.setStatus(UcloudMenuStatusEnum.ENABLE.getType());
           List<UcloudMenu> allMenuList=this.selectMenuList(ucloudMenu);
           Map<Long,UcloudMenu> map=Maps.newHashMap();
           for (final UcloudMenu menu:allMenuList){
               map.put(menu.getPid(),menu);
           }
           for (final UcloudMenu menu:ownMenuList){
               getPid(menuSet,menu,map);
           }
           menuList=new ArrayList<>(menuSet);
       }
        List<MenuVo> list=this.getMenuVo(menuList);
        if (PublicUtil.isNotEmpty(menuVoList)){
            list.addAll(menuVoList);
        }
        // 2.递归成树
        return TreeUtil.getChildMenuVos(list, 0L);
    }

    private void getPid(Set<UcloudMenu> set,UcloudMenu menu,Map<Long,UcloudMenu> map){
        UcloudMenu parent=map.get(menu.getPid());
        if (parent!=null&&parent.getPid()!=0){
            getPid(set,parent,map);
        }
    }

    private List<MenuVo>  getMenuVo(List<UcloudMenu> list){
        List<MenuVo> voList=Lists.newArrayList();
        for (UcloudMenu menu:list){
            MenuVo vo=new MenuVo();
            BeanUtils.copyProperties(menu , vo);
            vo.setMenuName(menu.getMenuName());
            vo.setUrl(menu.getUrl());
            voList.add(vo);
        }
        return voList;
    }
    /**
     * 根据菜单ids列表查询菜单列表
     * @param ids
     * @return
     */
    @Override
    public List<UcloudMenu> getMenuList(Set<Long> ids) {
        return ucloudMenuMapper.listMenu(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,readOnly = true)
    public List<UcloudMenu> selectMenuList(UcloudMenu ucloudMenu) {
        return ucloudMenuMapper.selectMenuList(ucloudMenu);
    }

    @Override
    public List<UcloudMenu> listMenuListByRoleId(Long roleId) {
        List<UcloudMenu> menuList=ucloudMenuMapper.listMenuListByRoleId(roleId);
        List<UcloudMenu> addMenuList=Lists.newArrayList();
        if (PublicUtil.isNotEmpty(menuList)) {
            menuList.stream().forEach(a->{
               this.getMenuList(addMenuList,a.getPid());
            });
        }
        menuList.addAll(addMenuList);
        return new ArrayList<>(new HashSet<>(menuList));
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ViewMenuVo getViewVoById(Long id) {
        Preconditions.checkArgument(id != null, ErrorCodeEnum.UCLOUD10014001.getMsg());
        UcloudMenu menu=ucloudMenuMapper.selectByPrimaryKey(id);
        if (menu==null){
            log.error("没有找到要修改的菜单 id={}",id);
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10014002,id);
        }
        // 获取父级菜单信息
        UcloudMenu parentMenu = ucloudMenuMapper.selectByPrimaryKey(menu.getPid());
        ModelMapper modelMapper = new ModelMapper();
        ViewMenuVo menuVo = modelMapper.map(menu, ViewMenuVo.class);
        if (parentMenu != null) {
            menuVo.setParentMenuName(parentMenu.getMenuName());
        }
        return menuVo;
    }

    @Override
    public void updateUcloudMenuStatus(UcloudMenuStatusDto ucloudMenuStatusDto, LoginAuthDto loginAuthDto) {
        Long id = ucloudMenuStatusDto.getId();
        String status = ucloudMenuStatusDto.getStatus();
        Preconditions.checkArgument(id != null, "菜单ID不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(status), "菜单状态不能为空");
        UcloudMenu ucloudMenu=this.selectByKey(id);
        if (MenuConstant.MENU_LEVEL_ROOT.equals(ucloudMenu.getLeaf())){
            throw new  UcloudBizException(ErrorCodeEnum.UCLOUD10014003);
        }
        List<UcloudMenu> menuList=Lists.newArrayList();
        int  result;
        if (UcloudMenuStatusEnum.DISABLE.getType().equals(status)){
           //获取菜单以及子菜单
           menuList=this.getAllChildMenuByMenuId(id, UcloudMenuStatusEnum.ENABLE.getType());
           //禁用菜单以及子菜单
            result= this.disableMenuList(menuList,loginAuthDto);
        }else {
           // 获取菜单、其子菜单以及父菜单
            UcloudMenu uMenu = new UcloudMenu();
            uMenu.setPid(id);
            result = this.selectCount(uMenu);
            // 此菜单含有子菜单
            if (result > 0) {
                menuList = this.getAllChildMenuByMenuId(id, UcloudMenuStatusEnum.DISABLE.getType());
            }
            List<UcloudMenu> menuListTemp = this.getAllParentMenuByMenuId(id);
            for (UcloudMenu menu : menuListTemp) {
                if (!menuList.contains(menu)) {
                    menuList.add(menu);
                }
            }
            // 启用菜单、其子菜单以及父菜单
            result = this.enableMenuList(menuList, loginAuthDto);
        }
        if (result < 1) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10014006, id);
        }

    }

    @Override
    public List<UcloudMenu> getAllChildMenuByMenuId(Long menuId, String menuStatus) {
        UcloudMenu uacMenuQuery = new UcloudMenu();
        uacMenuQuery.setId(menuId);
        uacMenuQuery = mapper.selectOne(uacMenuQuery);
        List<UcloudMenu> ucloudMenuList = Lists.newArrayList();
        ucloudMenuList = buildNode(ucloudMenuList, uacMenuQuery, menuStatus);
        return ucloudMenuList;
    }

    @Override
    public int disableMenuList(List<UcloudMenu> menuList, LoginAuthDto loginAuthDto) {
        UcloudMenu uacMenuUpdate = new UcloudMenu();
        int sum = 0;
        for (UcloudMenu menu : menuList) {
            uacMenuUpdate.setId(menu.getId());
            uacMenuUpdate.setVersion(menu.getVersion() + 1);
            uacMenuUpdate.setStatus(UcloudMenuStatusEnum.DISABLE.getType());
            uacMenuUpdate.setLastOperator(loginAuthDto.getLoginName());
            uacMenuUpdate.setLastOperatorId(loginAuthDto.getUserId());
            uacMenuUpdate.setUpdateTime(new Date());
            int result = mapper.updateByPrimaryKeySelective(uacMenuUpdate);
            if (result > 0) {
                sum += 1;
            } else {
                throw new UcloudBizException(ErrorCodeEnum.UCLOUD10014004, menu.getId());
            }
        }
        return sum;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<UcloudMenu> getAllParentMenuByMenuId(Long id) {
        UcloudMenu ucloudMenuQuery = new UcloudMenu();
        ucloudMenuQuery.setId(id);
        ucloudMenuQuery = mapper.selectOne(ucloudMenuQuery);
        List<UcloudMenu> uacMenuList = Lists.newArrayList();
        uacMenuList = buildParentNote(uacMenuList, ucloudMenuQuery);
        return uacMenuList;
    }

    @Override
    public int enableMenuList(List<UcloudMenu> menuList, LoginAuthDto loginAuthDto) {
        UcloudMenu ucloudMenuUpdate = new UcloudMenu();
        int sum = 0;
        for (UcloudMenu menu : menuList) {
            ucloudMenuUpdate.setId(menu.getId());
            ucloudMenuUpdate.setVersion(menu.getVersion() + 1);
            ucloudMenuUpdate.setStatus(UcloudMenuStatusEnum.ENABLE.getType());
            ucloudMenuUpdate.setLastOperator(loginAuthDto.getLoginName());
            ucloudMenuUpdate.setLastOperatorId(loginAuthDto.getUserId());
            ucloudMenuUpdate.setUpdateTime(new Date());
            int result = mapper.updateByPrimaryKeySelective(ucloudMenuUpdate);
            if (result > 0) {
                sum += 1;
            } else {
                throw new UcloudBizException(ErrorCodeEnum.UCLOUD10014005, menu.getId());
            }
        }
        return sum;
    }

    @Override
    public int saveUcloudMenu(UcloudMenu ucloudMenu, LoginAuthDto loginAuthDto) {
        Long pid = ucloudMenu.getPid();
        ucloudMenu.setUpdateInfo(loginAuthDto);
        UcloudMenu parentMenu = mapper.selectByPrimaryKey(pid);
        if (PublicUtil.isEmpty(parentMenu)) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10014007, pid);
        }
        if(ucloudMenu.isNew()){
            UcloudMenu updateMenu = new UcloudMenu();
            ucloudMenu.setLevel(parentMenu.getLevel() + 1);
            updateMenu.setLeaf(MenuConstant.MENU_LEAF_NO);
            updateMenu.setId(pid);
            Long menuId =1L;
            ucloudMenu.setId(menuId);
            int result = mapper.updateByPrimaryKeySelective(updateMenu);
            if (result < 1) {
                throw new UcloudBizException(ErrorCodeEnum.UCLOUD10014008, menuId);
            }

            ucloudMenu.setStatus(UcloudMenuStatusEnum.ENABLE.getType());
            ucloudMenu.setCreatorId(loginAuthDto.getUserId());
            ucloudMenu.setCreator(loginAuthDto.getUserName());
            ucloudMenu.setLastOperatorId(loginAuthDto.getUserId());
            ucloudMenu.setLastOperator(loginAuthDto.getUserName());
            // 新增的菜单是叶子节点
            ucloudMenu.setLeaf(MenuConstant.MENU_LEAF_YES);
            return ucloudMenuMapper.insertSelective(ucloudMenu);
        }else {
            return ucloudMenuMapper.updateByPrimaryKeySelective(ucloudMenu);
        }
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public boolean checkMenuHasChildMenu(Long pid) {
        Preconditions.checkArgument(pid != null, ErrorCodeEnum.UCLOUD10014010.getMsg());
        UcloudMenu uacMenu = new UcloudMenu();
        uacMenu.setStatus(UcloudMenuStatusEnum.ENABLE.getType());
        uacMenu.setPid(pid);

        return mapper.selectCount(uacMenu) > 0;
    }

    @Override
    public int deleteMenuByMenuId(Long id, LoginAuthDto loginAuthDto) {
        Preconditions.checkArgument(id!=null,ErrorCodeEnum.UCLOUD10014001.getMsg());
        int result;
        // 获取当前菜单信息
        UcloudMenu ucloudMenuQuery = new UcloudMenu();
        ucloudMenuQuery.setId(id);
        ucloudMenuQuery = mapper.selectOne(ucloudMenuQuery);
        if (PublicUtil.isEmpty(ucloudMenuQuery)) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10014002, id);
        }
        //删除菜单和角色的关联数据
        UcloudRoleMenu roleMenu=new UcloudRoleMenu();
        roleMenu.setMenuId(id);
        ucloudRoleMenuService.delete(roleMenu);
        //删除菜单
        result = ucloudMenuMapper.deleteByPrimaryKey(id);
        if (result < 1) {
            log.error("删除菜单失败 menuId={}", id);
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10014011, id);
        }
        //删除权限
        List<UcloudAction> ucloudActionList=ucloudActionService.findActionListByMenuId(id);
        if (PublicUtil.isNotEmpty(ucloudActionList)){
            ucloudActionService.deleteByMenuId(id);
        }
        // 修改当前删除菜单的父菜单是否是叶子节点
        UcloudMenu updateParentUcloudMenu = new UcloudMenu();
        updateParentUcloudMenu.setId(ucloudMenuQuery.getPid());
        updateParentUcloudMenu.setLeaf(MenuConstant.MENU_LEAF_YES);
        // 是二三级
        if (Objects.equals(MenuConstant.MENU_LEVEL_TWO, ucloudMenuQuery.getLevel())
                || Objects.equals(MenuConstant.MENU_LEVEL_THREE, ucloudMenuQuery.getLevel())) {
            // 查询是否是叶子节点
            int count = ucloudMenuMapper.selectMenuChildCountByPid(ucloudMenuQuery.getPid());
            if (count == 0) {
                ucloudMenuMapper.updateByPrimaryKeySelective(updateParentUcloudMenu);
            }
        }
        return result;
    }


    private List<UcloudMenu> getMenuList(List<UcloudMenu> uacMenuList, Long menuId) {
        UcloudMenu uacMenu = ucloudMenuMapper.selectByPrimaryKey(menuId);
        if (uacMenu != null) {
            Long pid = uacMenu.getPid();
            if (pid != null) {
                uacMenuList.add(uacMenu);
                getMenuList(uacMenuList, pid);
            }
        }
        return uacMenuList;
    }
    /**
     * 递归获取菜单的子菜单
     */
    private List<UcloudMenu> buildNode(List<UcloudMenu> uacMenuList, UcloudMenu ucloudMenu, String menuStatus) {
        List<UcloudMenu> uacMenuQueryList = mapper.select(ucloudMenu);
        UcloudMenu ucloudMenuQuery;
        for (UcloudMenu menu : uacMenuQueryList) {
            // 启用状态
            if (menuStatus.equals(menu.getStatus()) && !MenuConstant.MENU_LEVEL_ROOT.equals(menu.getLevel())) {
                uacMenuList.add(menu);
            }
            ucloudMenuQuery = new UcloudMenu();
            ucloudMenuQuery.setPid(menu.getId());
            buildNode(uacMenuList, ucloudMenuQuery, menuStatus);
        }
        return uacMenuList;
    }
    /**
     * 递归获取菜单的父菜单
     */
    private List<UcloudMenu> buildParentNote(List<UcloudMenu> ucloudMenuList, UcloudMenu ucloudMenu) {
        List<UcloudMenu> uacMenuQueryList = mapper.select(ucloudMenu);
        UcloudMenu ucloudMenuQuery;
        for (UcloudMenu menu : uacMenuQueryList) {
            if (UcloudMenuStatusEnum.DISABLE.getType().equals(menu.getStatus())
                    && !MenuConstant.MENU_LEVEL_ROOT.equals(menu.getLevel())) {
                // 禁用状态
                ucloudMenuList.add(menu);
            }
            ucloudMenuQuery = new UcloudMenu();
            ucloudMenuQuery.setId(menu.getPid());
            buildParentNote(ucloudMenuList, ucloudMenuQuery);
        }
        return ucloudMenuList;
    }
}
