package com.gaoyg.monkeyzicloud.provider.service.impl;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseService;
import com.gaoyg.monkeyzicloud.constant.GlobalConstant;
import com.gaoyg.monkeyzicloud.provider.enums.UcloudMenuStatusEnum;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudMenuMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudMenu;
import com.gaoyg.monkeyzicloud.provider.service.UcloudMenuService;
import com.gaoyg.monkeyzicloud.provider.utils.TreeUtil;
import com.gaoyg.monkeyzicloud.provider.vo.MenuVo;
import com.gaoyg.monkeyzicloud.util.pubutils.PublicUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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


}
