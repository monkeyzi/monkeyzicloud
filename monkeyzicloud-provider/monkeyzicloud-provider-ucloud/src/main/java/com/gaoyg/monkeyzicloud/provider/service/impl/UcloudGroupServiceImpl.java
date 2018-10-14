package com.gaoyg.monkeyzicloud.provider.service.impl;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseService;
import com.gaoyg.monkeyzicloud.constant.GlobalConstant;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.provider.enums.UcloudGroupStatusEnum;
import com.gaoyg.monkeyzicloud.provider.exception.UcloudBizException;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudGroupMapper;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudGroupUserMapper;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudRoleMapper;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudRoleUserMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudGroup;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudGroupUser;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudUser;
import com.gaoyg.monkeyzicloud.provider.model.dto.UserRole.BindUserDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.group.GroupBindUserDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.group.GroupBindUserReqDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.group.IdStatusDto;
import com.gaoyg.monkeyzicloud.provider.model.vo.group.GroupZtreeVo;
import com.gaoyg.monkeyzicloud.provider.service.UcloudGroupService;
import com.gaoyg.monkeyzicloud.provider.service.UcloudUserService;
import com.gaoyg.monkeyzicloud.provider.utils.TreeUtil;
import com.gaoyg.monkeyzicloud.provider.vo.MenuVo;
import com.gaoyg.monkeyzicloud.util.pubutils.PublicUtil;
import com.gaoyg.monkeyzicloud.util.util.Collections3;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author: 高yg
 * @date: 2018/9/18 21:48
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UcloudGroupServiceImpl extends BaseService<UcloudGroup> implements UcloudGroupService {
    @Resource
    private UcloudGroupMapper ucloudGroupMapper;
    @Resource
    private UcloudGroupUserMapper ucloudGroupUserMapper;
    @Resource
    private UcloudRoleUserMapper ucloudRoleUserMapper;
    @Resource
    private UcloudUserService ucloudUserService;
    @Resource
    private UcloudRoleMapper ucloudRoleMapper;

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public List<MenuVo> getGroupTreeListByUserId(Long userId) {
        UcloudGroupUser groupUser = ucloudGroupUserMapper.getByUserId(userId);
        Long groupId = groupUser.getGroupId();
        //查询当前登陆人所在的组织信息
        UcloudGroup currentUserUcloudGroup = ucloudGroupMapper.selectByPrimaryKey(groupId);
        //获取当前所选组织的所有子节点
        List<GroupZtreeVo> childUcloudGroupList = this.getGroupTree(currentUserUcloudGroup.getId());
        return this.buildGroupTree(childUcloudGroupList, groupId);
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public List<GroupZtreeVo> getGroupTree(Long id) {
        List<GroupZtreeVo> ztreeVoList=Lists.newArrayList();
        UcloudGroup ucloudGroup=ucloudGroupMapper.selectByPrimaryKey(id);
        GroupZtreeVo ztreeVo=buildGroupTreeVoByUcloudGroup(ucloudGroup);
        if (0L == ucloudGroup.getPid()) {
            ztreeVo.setOpen(true);
        }
        ztreeVoList.add(ztreeVo);

        UcloudGroup group = new UcloudGroup();
        group.setPid(id);
        List<UcloudGroup> groupList = ucloudGroupMapper.select(group);
        if (PublicUtil.isNotEmpty(groupList)) {
            ztreeVoList = buildNode(groupList, ztreeVoList);
        }
        return ztreeVoList;
    }

    @Override
    public int deleteUcloudGroupById(Long id) {
        Preconditions.checkArgument(id != null, ErrorCodeEnum.UCLOUD10016004.getMsg());
        Preconditions.checkArgument(!Objects.equals(id, GlobalConstant.Sys.SUPER_MANAGE_GROUP_ID),
                ErrorCodeEnum.UCLOUD10016005.getMsg());

        // 根据前台传入的组织参数校验该组织是否存在
        UcloudGroup ucloudGroup = ucloudGroupMapper.selectByPrimaryKey(id);
        if (PublicUtil.isEmpty(ucloudGroup)) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10016001, id);
        }
        //判断该组织下是否存在子节点
        UcloudGroup childGroup = new UcloudGroup();
        childGroup.setPid(id);
        List<UcloudGroup> childGroupList = ucloudGroupMapper.select(childGroup);
        if (PublicUtil.isNotEmpty(childGroupList)) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10016002, id);
        }
        //判断该组织下是否存在用户
        UcloudGroupUser ucloudGroupUser = new UcloudGroupUser();
        ucloudGroupUser.setGroupId(id);
        List<UcloudGroupUser> uacGroupUserList =ucloudGroupUserMapper.select(ucloudGroupUser);
        if (PublicUtil.isNotEmpty(uacGroupUserList)) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10016003, id);
        }
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int saveUcloudGroup(UcloudGroup group, LoginAuthDto loginAuthDto) {
        int result;
        Preconditions.checkArgument(!StringUtils.isEmpty(group.getPid()), "上级节点不能为空");

        UcloudGroup parenGroup = ucloudGroupMapper.selectByPrimaryKey(group.getPid());
        if (PublicUtil.isEmpty(parenGroup)) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10016006, group.getPid());
        }
        //设置组织的地址 TODO

        group.setUpdateInfo(loginAuthDto);

        if (group.isNew()) {
            //TODO
            Long groupId = 1L;
            group.setId(groupId);
            group.setLevel(parenGroup.getLevel() + 1);
            result = this.addUacGroup(group);
        } else {
            result = this.editUacGroup(group);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public UcloudGroup getGroupById(Long id) {
        UcloudGroup ucloudGroup = ucloudGroupMapper.selectByPrimaryKey(id);
        if (PublicUtil.isEmpty(ucloudGroup)) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10016001, id);
        }
        UcloudGroup parentGroup = ucloudGroupMapper.selectByPrimaryKey(ucloudGroup.getPid());
        if (parentGroup != null) {
            ucloudGroup.setParentGroupCode(parentGroup.getGroupCode());
            ucloudGroup.setParentGroupName(parentGroup.getGroupName());
        }
        // 处理级联菜单回显地址
        Long provinceId = ucloudGroup.getProvinceId();
        Long cityId = ucloudGroup.getCityId();
        Long areaId = ucloudGroup.getAreaId();
        Long streetId = ucloudGroup.getStreetId();
        List<Long> addressList = Lists.newArrayList();
        if (provinceId != null) {
            addressList.add(provinceId);
        }
        if (cityId != null) {
            addressList.add(cityId);
        }
        if (areaId != null) {
            addressList.add(areaId);
        }
        if (streetId != null) {
            addressList.add(streetId);
        }
        ucloudGroup.setAddressList(addressList);
        return ucloudGroup;
    }

    @Override
    public int updateUcloudGroupStatusById(IdStatusDto idStatusDto, LoginAuthDto loginAuthDto) {
        Long groupId = idStatusDto.getId();
        Integer status = idStatusDto.getStatus();

        UcloudGroup ucloudGroup = new UcloudGroup();
        ucloudGroup.setId(groupId);
        ucloudGroup.setStatus(status);

        UcloudGroup group = ucloudGroupMapper.selectByPrimaryKey(groupId);
        if (PublicUtil.isEmpty(group)) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10016001, groupId);
        }
        if (!UcloudGroupStatusEnum.contains(status)) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10016007);
        }
        //查询所有的组织
        List<UcloudGroup> totalGroupList = ucloudGroupMapper.selectAll();
        List<GroupZtreeVo> totalList = Lists.newArrayList();
        GroupZtreeVo zTreeVo;
        for (UcloudGroup vo : totalGroupList) {
            zTreeVo = new GroupZtreeVo();
            zTreeVo.setId(vo.getId());
            totalList.add(zTreeVo);
        }

        UcloudGroupUser ucloudGroupUser = new UcloudGroupUser();
        ucloudGroupUser.setUserId(loginAuthDto.getUserId());
        UcloudGroupUser groupUser = ucloudGroupUserMapper.selectOne(ucloudGroupUser);
        // 查询当前登陆人所在的组织信息
        UcloudGroup currentUserUacGroup = ucloudGroupMapper.selectByPrimaryKey(groupUser.getGroupId());
        // 查询当前登陆人能禁用的所有子节点
        List<GroupZtreeVo> childGroupList = this.getGroupTree(currentUserUacGroup.getId());
        // 计算不能禁用的组织= 所有的组织 - 禁用的所有子节点
        totalList.removeAll(childGroupList);
        // 判断所选的组织是否在不能禁用的列表里
        GroupZtreeVo zTreeVo1 = new GroupZtreeVo();
        zTreeVo1.setId(group.getId());
        if (totalList.contains(zTreeVo1)) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011023);
        }
        if (groupUser.getGroupId().equals(ucloudGroup.getId()) && UcloudGroupStatusEnum.ENABLE.getStatus() == group.getStatus()) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011023);
        }
        ucloudGroup.setGroupName(group.getGroupName());
        ucloudGroup.setGroupCode(group.getGroupCode());
        ucloudGroup.setVersion(group.getVersion() + 1);
        int result = ucloudGroupMapper.updateByPrimaryKeySelective(ucloudGroup);
        // 获取当前所选组织的所有子节点
        List<GroupZtreeVo> childUacGroupList = this.getGroupTree(ucloudGroup.getId());
        // 批量修改组织状态
        if (PublicUtil.isNotEmpty(childUacGroupList)) {
            UcloudGroup childGroup;
            for (GroupZtreeVo uacGroup1 : childUacGroupList) {
                if (UcloudGroupStatusEnum.ENABLE.getStatus() == status) {
                    UcloudGroup parentGroup = ucloudGroupMapper.selectByPrimaryKey(uacGroup1.getpId());
                    if (parentGroup.getStatus() == UcloudGroupStatusEnum.DISABLE.getStatus()) {
                        throw new UcloudBizException(ErrorCodeEnum.UCLOUD10016008);
                    }
                }
                childGroup = new UcloudGroup();
                childGroup.setStatus(ucloudGroup.getStatus());
                childGroup.setId(uacGroup1.getId());
                result = ucloudGroupMapper.updateByPrimaryKeySelective(childGroup);
                if (result < 1) {
                    throw new UcloudBizException(ErrorCodeEnum.UCLOUD10016009, uacGroup1.getId());
                }
            }
        }
        return result;
    }

    @Override
    public void groupBindUser(GroupBindUserReqDto groupBindUserReqDto, LoginAuthDto loginAuthDto) {
        if (groupBindUserReqDto == null) {
            log.error("参数不能为空");
            throw new IllegalArgumentException("参数不能为空");
        }

        Long groupId = groupBindUserReqDto.getGroupId();
        Long loginUserId = loginAuthDto.getUserId();
        List<Long> userIdList = groupBindUserReqDto.getUserIdList();

        if (null == groupId) {
            throw new IllegalArgumentException(ErrorCodeEnum.UCLOUD10016004.getMsg());
        }

        UcloudGroup group = ucloudGroupMapper.selectByPrimaryKey(groupId);

        if (group == null) {
            log.error("找不到角色信息 groupId={}", groupId);
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10016001, groupId);
        }

        if (PublicUtil.isNotEmpty(userIdList) && userIdList.contains(loginUserId)) {
            log.error("不能操作当前登录用户 userId={}", loginUserId);
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011023);
        }

        // 查询超级管理员用户Id集合
        List<Long> superUserList = ucloudRoleUserMapper.listSuperUser(GlobalConstant.Sys.SUER_MANAGE_ROLE_ID);
        List<Long> unionList = Collections3.intersection(userIdList, superUserList);
        if (PublicUtil.isNotEmpty(userIdList) && PublicUtil.isNotEmpty(unionList)) {
            log.error("不能操作超级管理员用户 超级用户={}", unionList);
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011023);
        }

        // 1. 先取消对该角色的用户绑定(不包含超级管理员用户)
        List<UcloudGroupUser> groupUsers = ucloudGroupUserMapper.listByGroupId(groupId);

        if (PublicUtil.isNotEmpty(groupUsers)) {
            ucloudGroupUserMapper.deleteExcludeSuperMng(groupId, GlobalConstant.Sys.SUER_MANAGE_ROLE_ID);
        }

        if (PublicUtil.isEmpty(userIdList)) {
            // 取消该角色的所有用户的绑定
            log.info("取消绑定所有非超级管理员用户成功");
            return;
        }

        // 绑定所选用户
        for (Long userId : userIdList) {
            UcloudUser ucloudUser = ucloudUserService.queryUserById(userId);
            if (PublicUtil.isEmpty(ucloudUser)) {
                log.error("找不到绑定的用户 userId={}", userId);
                throw new UcloudBizException(ErrorCodeEnum.UCLOUD10011011, userId);
            }
            UcloudGroupUser ucloudGroupUser = new UcloudGroupUser();
            ucloudGroupUser.setUserId(userId);
            ucloudGroupUser.setGroupId(groupId);
            ucloudGroupUserMapper.insertSelective(ucloudGroupUser);
        }
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public GroupBindUserDto getGroupBindUserData(Long groupId, Long userId) {
        GroupBindUserDto groupBindUserDto = new GroupBindUserDto();
        Set<Long> alreadyBindUserIdSet = Sets.newHashSet();
        UcloudGroup ucloudGroup = ucloudGroupMapper.selectByPrimaryKey(groupId);
        if (PublicUtil.isEmpty(ucloudGroup)) {
            log.error("找不到uacGroup={}, 的组织", ucloudGroup);
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10016001, groupId);
        }

        // 查询所有用户包括已禁用的用户
        List<BindUserDto> bindUserDtoList = ucloudRoleMapper.
                selectAllNeedBindUser(GlobalConstant.Sys.SUER_MANAGE_ROLE_ID, userId);
        // 该组织已经绑定的用户
        List<UcloudGroupUser> setAlreadyBindUserSet = ucloudGroupUserMapper.listByGroupId(groupId);

        Set<BindUserDto> allUserSet = new HashSet<>(bindUserDtoList);

        for (UcloudGroupUser ucloudGroupUser : setAlreadyBindUserSet) {
            alreadyBindUserIdSet.add(ucloudGroupUser.getUserId());
        }

        groupBindUserDto.setAllUserSet(allUserSet);
        groupBindUserDto.setAlreadyBindUserIdSet(alreadyBindUserIdSet);

        return groupBindUserDto;
    }

    /**
     * 新增组织
     * @param group
     * @return
     */
    private int addUacGroup(UcloudGroup group) {
        if (StringUtils.isEmpty(group.getStatus())) {
            group.setStatus(UcloudGroupStatusEnum.ENABLE.getStatus());
        }
        return ucloudGroupMapper.insertSelective(group);
    }

    /**
     * 修改组织
     * @param group
     * @return
     */
    private int editUacGroup(UcloudGroup group) {
        return ucloudGroupMapper.updateByPrimaryKeySelective(group);
    }
    private List<GroupZtreeVo> buildNode(List<UcloudGroup> groupList, List<GroupZtreeVo> tree) {
        for (UcloudGroup group : groupList) {

            GroupZtreeVo groupZTreeVo = buildGroupTreeVoByUcloudGroup(group);

            if (0L == group.getPid()) {
                groupZTreeVo.setOpen(true);
            }
            // 设置根节点
            tree.add(groupZTreeVo);

            UcloudGroup query = new UcloudGroup();
            query.setPid(group.getId());

            List<UcloudGroup> groupChildrenList = ucloudGroupMapper.select(query);
            // 有子节点 递归查询
            if (PublicUtil.isNotEmpty(groupChildrenList)) {
                buildNode(groupChildrenList, tree);
            }
        }
        return tree;
    }

    /**
     * UcloudGroup  转为  GroupZtreeVo
     * @param group
     * @return
     */
    private GroupZtreeVo buildGroupTreeVoByUcloudGroup(UcloudGroup group) {
        GroupZtreeVo vo=new GroupZtreeVo();
        vo.setId(group.getId());
        vo.setpId(group.getPid());
        vo.setName(group.getGroupName());
        vo.setType(group.getType());
        vo.setStatus(group.getStatus());
        vo.setLeaf(group.getLevel());
        vo.setLevel(group.getLevel());
        vo.setGroupCode(group.getGroupCode());
        vo.setContact(group.getContact());
        vo.setContactPhone(group.getContactPhone());
        vo.setCreatedTime(group.getCreatedTime() == null ? new Date() : group.getCreatedTime());
        vo.setCreator(group.getCreator());
        vo.setGroupAddress(group.getGroupAddress());
        vo.setGroupName(group.getGroupName());
        return vo;
    }

    /**
     * 组装组织树
     * @param childUcloudGroupList
     * @param currentGroupId
     * @return
     */
    private List<MenuVo> buildGroupTree(List<GroupZtreeVo> childUcloudGroupList, Long currentGroupId) {
        List<MenuVo> listVo = Lists.newArrayList();
        MenuVo menuVo;
        for (GroupZtreeVo group : childUcloudGroupList) {
            menuVo = new MenuVo();
            menuVo.setId(group.getId());
            if (currentGroupId.equals(group.getId())) {
                menuVo.setPid(0L);
            } else {
                menuVo.setPid(group.getpId());
            }
            menuVo.setMenuCode(group.getGroupCode());
            menuVo.setMenuName(group.getGroupName());
            listVo.add(menuVo);
        }

        return TreeUtil.getChildMenuVos(listVo, 0L);
    }
}
