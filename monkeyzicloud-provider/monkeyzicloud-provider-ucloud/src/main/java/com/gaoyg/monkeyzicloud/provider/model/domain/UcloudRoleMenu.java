package com.gaoyg.monkeyzicloud.provider.model.domain;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.BaseEntity;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: 高yg
 * @date: 2018/8/21 23:29
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@Alias(value = "ucloudRoleMenu")
@Table(name="ucloud_role_menu")
public class UcloudRoleMenu extends BaseEntity {

    private static final long serialVersionUID = -9052683954152822756L;
    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Id
    @Column(name = "menu_id")
    private Long menuId;
}
