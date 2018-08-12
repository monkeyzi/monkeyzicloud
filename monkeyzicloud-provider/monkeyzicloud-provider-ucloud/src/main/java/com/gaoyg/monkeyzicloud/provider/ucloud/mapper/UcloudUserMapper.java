package com.gaoyg.monkeyzicloud.provider.ucloud.mapper;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.MyMapper;
import com.gaoyg.monkeyzicloud.provider.ucloud.model.domain.UcloudUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author: 高yg
 * @date: 2018/8/5 20:32
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Component
@Mapper
public interface UcloudUserMapper extends MyMapper<UcloudUser> {
  UcloudUser findByLoginName(String loginName);

  UcloudUser selectUserInfoByUserId(Long userId);
}
