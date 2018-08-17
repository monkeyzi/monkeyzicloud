package com.gaoyg.monkeyzicloud.provider.mapper;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.MyMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudLog;
import com.gaoyg.monkeyzicloud.provider.model.dto.log.QueryLogDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/12 17:41
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Component
@Mapper
public interface UcloudLogMapper  extends MyMapper<UcloudLog> {
    List<UcloudLog> selectUserLogListByUserId(@Param("userId") Long userId);
    List<UcloudLog> queryLogListWithPage(QueryLogDto queryLogDto);
}
