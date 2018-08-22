package com.gaoyg.monkeyzicloud.provider.mapper;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.MyMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudRoleAction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/21 23:39
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Mapper
@Component
public interface UcloudRoleActionMapper extends MyMapper<UcloudRoleAction>{

    /**
     * delete by actionId
     * @param actionId
     * @return
     */
    int deleteByActionId(@Param("actionId") Long actionId);

    /**
     * deleteBy roleIds
     * @param roleIdList
     * @return
     */
    int deleteByRoleIdList(@Param("roleIdList") List<Long> roleIdList);
}
