package com.gaoyg.monkeyzicloud.provider.model.domain;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.BaseEntity;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.annotation.Target;

/**
 * @author: 高yg
 * @date: 2018/8/21 22:19
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@Alias("ucloudRoleUser")
@Table(name = "ucloud_role_user")
public class UcloudRoleUser extends BaseEntity {

    private static final long serialVersionUID = -4598936929315554832L;
    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Id
    @Column(name = "user_id")
    private Long userId;
}
