package com.gaoyg.monkeyzicloud.provider.mapper;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.MyMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudGroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/9/18 21:50
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Component
@Mapper
public interface UcloudGroupMapper extends MyMapper<UcloudGroup> {
    List<UcloudGroup> selectListBy();
}
