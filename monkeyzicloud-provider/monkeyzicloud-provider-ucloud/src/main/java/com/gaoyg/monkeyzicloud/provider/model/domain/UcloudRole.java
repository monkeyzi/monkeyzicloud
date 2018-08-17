package com.gaoyg.monkeyzicloud.provider.model.domain;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author: 高yg
 * @date: 2018/8/16 21:30
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ucloud_role")
@Alias(value = "ucloudRole")
public class UcloudRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6049575043793281879L;
    /**
     * 角色编码
     */
    @Column(name = "role_code")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "{role.roleCode.pattern}")
    @Length(min = 6, max = 20, message = "{role.roleCode.length}")
    @NotNull(message = "{role.roleCode.notNull}")
    private String roleCode;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    @Pattern(regexp = "^[\\u4e00-\\u9faf]+$", message = "{role.roleName.pattern}")
    @Length(min = 4, max = 10, message = "{role.roleName.length}")
    @NotNull(message = "{role.roleName.notNull}")
    private String roleName;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    @Length(max = 150, message = "{role.remark.length}")
    private String remark;
}
