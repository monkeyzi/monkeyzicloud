package com.gaoyg.monkeyzicloud.provider.model.domain;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/9/2 20:30
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ucloud_action")
@Alias(value = "ucloudAction")
public class UcloudAction extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 6943147531573339665L;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 权限名称
     */
    @Column(name = "action_name")
    private String actionName;

    /**
     * 权限编码
     */
    @Column(name = "action_code")
    private String actionCode;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 菜单ID
     */
    @Column(name = "menu_id")
    private Long menuId;

    /**
     * 菜单ID
     */
    @Transient
    private List<Long> menuIdList;
}