package com.gaoyg.monkeyzicloud.provider.model.dto.action;

import com.gaoyg.monkeyzicloud.dto.BaseQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/9/17 22:13
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "ActionMainQueryDto")
public class ActionMainQueryDto extends BaseQuery {
    private static final long serialVersionUID = -1755881173841393763L;
    /**
     * 资源路径
     */
    private String url;

    /**
     * 权限名称
     */
    private String actionName;

    /**
     * 权限编码
     */
    private String actionCode;

    /**
     * 状态
     */
    private String status;

    /**
     * 菜单ID
     */
    private List<Long> menuIdList;
}
