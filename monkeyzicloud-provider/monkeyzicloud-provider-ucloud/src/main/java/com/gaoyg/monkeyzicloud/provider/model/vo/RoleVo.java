package com.gaoyg.monkeyzicloud.provider.model.vo;

import com.gaoyg.monkeyzicloud.dto.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: 高yg
 * @date: 2018/8/16 21:28
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleVo extends BaseVo {
    private static final long serialVersionUID = 3819529748816533170L;

    /**
     * 角色编码
     */
    private String roleCode;

    private String roleName;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;
}
