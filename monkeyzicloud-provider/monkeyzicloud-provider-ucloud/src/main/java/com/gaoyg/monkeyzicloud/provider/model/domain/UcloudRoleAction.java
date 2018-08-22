package com.gaoyg.monkeyzicloud.provider.model.domain;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.BaseEntity;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: 高yg
 * @date: 2018/8/21 23:37
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@Alias(value = "ucloudRoleAction")
@Table(name="ucloud_role_action")
public class UcloudRoleAction extends BaseEntity {
    private static final long serialVersionUID = -4240611881810188284L;

    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Id
    @Column(name = "action_id")
    private Long actionId;
}
