package com.gaoyg.monkeyzicloud.provider.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.dto.OperationLogDto;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudLog;
import com.gaoyg.monkeyzicloud.provider.model.dto.log.QueryLogDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/12 17:34
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudLogService extends IBaseService<UcloudLog> {
    /**
     * save Login log
     * @param ulog
     * @param loginAuthDto
     * @return
     */
    int saveLog(UcloudLog ulog, LoginAuthDto loginAuthDto);

    /**
     * save operation Log
     * @param operationLogDto
     * @return
     */
    Integer saveOperationLog(OperationLogDto operationLogDto);

    /**
     * qyeryLogList by page
     * @param queryLogDto
     * @return
     */
    PageInfo queryLogWithPage(QueryLogDto queryLogDto);

    /**
     * query userLogList By userId
     * @param userId
     * @return
     */
    List<UcloudLog> queryUserLogListByUserId(Long userId);




}
