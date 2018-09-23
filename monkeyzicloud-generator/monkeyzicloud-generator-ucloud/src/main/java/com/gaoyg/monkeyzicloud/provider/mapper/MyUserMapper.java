package com.gaoyg.monkeyzicloud.provider.mapper;

import com.gaoyg.monkeyzicloud.provider.model.domain.MyUser;
import java.util.List;
import java.util.Map;

public interface MyUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MyUser record);

    int insertSelective(MyUser record);

    MyUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MyUser record);

    int updateByPrimaryKey(MyUser record);

    int insertBatch(List recordList);

    int selectCount(Map map);

    List selectBeans(Map map);
}