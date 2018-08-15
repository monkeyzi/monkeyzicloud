package com.gaoyg.monkeyzicloud.provider.ucloud.service.impl;

import com.gaoyg.monkeyzicloud.commom.core.support.BaseService;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.dto.OperationLogDto;
import com.gaoyg.monkeyzicloud.provider.ucloud.mapper.UcloudLogMapper;
import com.gaoyg.monkeyzicloud.provider.ucloud.model.domain.UcloudLog;
import com.gaoyg.monkeyzicloud.provider.ucloud.model.dto.log.QueryLogDto;
import com.gaoyg.monkeyzicloud.provider.ucloud.service.UcloudLogService;
import com.gaoyg.monkeyzicloud.util.pubutils.PublicUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/12 17:38
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UcloudLogServiceImpl extends BaseService<UcloudLog> implements UcloudLogService {

    @Autowired
    private UcloudLogMapper ucloudLogMapper;
    /**
     * 记录登录log
     * @param ulog
     * @param loginAuthDto
     * @return
     */
    @Override
    public int saveLog(UcloudLog ulog, LoginAuthDto loginAuthDto) {
        log.info("记录日志 {}=",ulog);
        ulog.setUpdateInfo(loginAuthDto);
        return ucloudLogMapper.insertSelective(ulog);
    }

    /**
     * 保存操作日志
     * @param operationLogDto
     * @return
     */
    @Override
    public Integer saveOperationLog(OperationLogDto operationLogDto) {
        log.info("记录操作日志 {}=",operationLogDto);
        ModelMapper modelMapper=new ModelMapper();
        UcloudLog ucloudLog = modelMapper.map(operationLogDto, UcloudLog.class);
        return ucloudLogMapper.insertSelective(ucloudLog);
    }

    /**
     * 日志列表查询
     * @param queryLogDto
     * @return
     */
    @Override
    public PageInfo queryLogWithPage(QueryLogDto queryLogDto) {
        List<Long> menuIdList=queryLogDto.getMenuIdList();
        Long  actionId=null;
        if (PublicUtil.isNotEmpty(menuIdList)){
            actionId=menuIdList.get(menuIdList.size()-1);
        }
        queryLogDto.setMenuId(actionId);
        PageHelper.startPage(queryLogDto.getPageNum(),queryLogDto.getPageSize());
        List<UcloudLog> actionList=ucloudLogMapper.queryLogListWithPage(queryLogDto);
        return new PageInfo(actionList);
    }

    /**
     * 根据用户的编号查询用户的操作日志
     * @param userId
     * @return
     */
    @Override
    public List<UcloudLog> queryUserLogListByUserId(Long userId) {
        return ucloudLogMapper.selectUserLogListByUserId(userId);
    }
}
