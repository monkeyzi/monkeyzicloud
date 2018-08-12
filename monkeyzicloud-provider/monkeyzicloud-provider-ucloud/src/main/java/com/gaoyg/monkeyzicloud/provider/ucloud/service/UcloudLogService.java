package com.gaoyg.monkeyzicloud.provider.ucloud.service;

import com.gaoyg.monkeyzicloud.commom.core.support.IBaseService;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.dto.OperationLogDto;
import com.gaoyg.monkeyzicloud.provider.ucloud.model.domain.UcloudLog;

/**
 * @author: 高yg
 * @date: 2018/8/12 17:34
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface UcloudLogService extends IBaseService<UcloudLog> {
    int saveLog(UcloudLog ulog, LoginAuthDto loginAuthDto);
    Integer saveOperationLog(OperationLogDto operationLogDto);
}
