package com.gaoyg.monkeyzicloud.provider.mapper;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.MyMapper;
import com.gaoyg.monkeyzicloud.provider.model.domain.UcloudUser;
import com.gaoyg.monkeyzicloud.provider.model.dto.role.BindRoleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

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

  List<UcloudUser> selectUserList(UcloudUser uacUser);

  int updateUcloudUser(UcloudUser user);

  /**
   * Select all need bind role list.
   *
   * @param superManagerRoleId the super manager role id
   *
   * @return the list
   */
  List<BindRoleDto> selectAllNeedBindRole(@Param("superManagerRoleId") Long superManagerRoleId);
}
