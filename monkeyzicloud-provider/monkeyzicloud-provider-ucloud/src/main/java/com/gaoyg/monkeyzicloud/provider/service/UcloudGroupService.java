package com.gaoyg.monkeyzicloud.provider.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudGroup;
import com.gaoyg.monkeyzicloud.provider.model.dto.group.GroupBindUserDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.group.GroupBindUserReqDto;
import com.gaoyg.monkeyzicloud.provider.model.dto.group.IdStatusDto;
import com.gaoyg.monkeyzicloud.provider.model.vo.group.GroupZtreeVo;
import com.gaoyg.monkeyzicloud.provider.vo.MenuVo;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/9/18 21:48
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudGroupService extends IBaseService {
    /**
     * 根据用户Id获取用户的组织树
     * @param userId
     * @return
     */
    List<MenuVo> getGroupTreeListByUserId(Long userId);

    /**
     * 根据组织Id获取该组织树
     * @param id
     * @return
     */
    List<GroupZtreeVo> getGroupTree(Long id);

    /**
     * 根据组织id删除组织
     * @param id
     * @return
     */
    int deleteUcloudGroupById(Long id);

    /**
     * 新增或者修改组织信息
     * @param group
     * @param loginAuthDto
     * @return
     */
    int saveUcloudGroup(UcloudGroup group, LoginAuthDto loginAuthDto);

    /**
     * 获取组织的详情
     * @param id
     * @return
     */
    UcloudGroup getGroupById(Long id);

    /**
     * 修改组织的状态
     * @param idStatusDto
     * @param loginAuthDto
     * @return
     */
    int updateUcloudGroupStatusById(IdStatusDto idStatusDto, LoginAuthDto loginAuthDto);

    /**
     * 组织绑定用户
     * @param groupBindUserReqDto
     * @param loginAuthDto
     */
    void groupBindUser(GroupBindUserReqDto groupBindUserReqDto, LoginAuthDto loginAuthDto);

    /**
     * 组织绑定用户页面的数据
     * @param groupId
     * @param userId
     * @return
     */
    GroupBindUserDto getGroupBindUserData(Long groupId, Long userId);
}
