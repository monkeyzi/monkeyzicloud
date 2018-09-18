package com.gaoyg.monkeyzicloud.provider.service.impl;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseService;
import com.gaoyg.monkeyzicloud.constant.GlobalConstant;
import com.gaoyg.monkeyzicloud.enums.ErrorCodeEnum;
import com.gaoyg.monkeyzicloud.provider.exception.UcloudBizException;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudActionMapper;
import com.gaoyg.monkeyzicloud.provider.mapper.UcloudRoleActionMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudAction;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudMenu;
import com.gaoyg.monkeyzicloud.provider.model.dto.action.ActionMainQueryDto;
import com.gaoyg.monkeyzicloud.provider.model.vo.action.ActionVo;
import com.gaoyg.monkeyzicloud.provider.service.UcloudActionService;
import com.gaoyg.monkeyzicloud.util.pubutils.PublicUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UcloudActionServiceImpl extends BaseService<UcloudAction> implements UcloudActionService {
    @Autowired
    private UcloudActionMapper ucloudActionMapper;
    @Autowired
    private UcloudRoleActionMapper ucloudRoleActionMapper;

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

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<UcloudAction> findActionListByMenuId(Long menuId) {
        if (menuId==null){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10014001,menuId);
        }
        return ucloudActionMapper.findActionListByMenuId(menuId);
    }

    @Override
    public int deleteByMenuId(Long menuId) {
        if (menuId==null){
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10014001,menuId);
        }
        return ucloudActionMapper.deleteByMenuId(menuId);
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public PageInfo queryActionListWithPage(ActionMainQueryDto actionMainQueryDto) {
        List<Long> menuIdList = actionMainQueryDto.getMenuIdList();
        Long menuId = null;
        if (PublicUtil.isNotEmpty(menuIdList)) {
            menuId = menuIdList.get(menuIdList.size() - 1);
        }
        UcloudAction ucloudAction = new UcloudAction();
        ucloudAction.setMenuId(menuId);
        BeanUtils.copyProperties(actionMainQueryDto, ucloudAction);
        ucloudAction.setOrderBy("update_time desc");
        PageHelper.startPage(actionMainQueryDto.getPageNum(), actionMainQueryDto.getPageSize());
        List<ActionVo> actionList = ucloudActionMapper.queryActionListWithPage(ucloudAction);
        return new PageInfo<>(actionList);
    }

    @Override
    public int deleteActionById(Long actionId) {
        //查询该角色下是否有用户绑定, 有的话提醒不能删除
        if (null == actionId) {
            throw new IllegalArgumentException("权限ID不能为空");
        }

        UcloudAction ucloudAction = ucloudActionMapper.selectByPrimaryKey(actionId);
        if (ucloudAction == null) {
            log.error("找不到权限信息 actionId={}", actionId);
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10015002, actionId);
        }

        // 删除角色权限表数据
        ucloudRoleActionMapper.deleteByActionId(actionId);

        return ucloudActionMapper.deleteByPrimaryKey(actionId);
    }

    @Override
    public void batchDeleteByIdList(List<Long> deleteIdList) {
        log.info("批量删除权限. deleteIdList={}", deleteIdList);
        Preconditions.checkArgument(PublicUtil.isNotEmpty(deleteIdList), ErrorCodeEnum.UCLOUD10015004.getMsg());
        int result = ucloudActionMapper.batchDeleteByIdList(deleteIdList);
        if (result < deleteIdList.size()) {
            throw new UcloudBizException(ErrorCodeEnum.UCLOUD10015003,
                    Joiner.on(GlobalConstant.Symbol.COMMA).join(deleteIdList));
        }
    }
}
