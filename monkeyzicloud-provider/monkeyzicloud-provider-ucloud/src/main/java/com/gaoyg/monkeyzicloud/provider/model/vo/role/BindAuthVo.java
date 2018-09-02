package com.gaoyg.monkeyzicloud.provider.model.vo.role;

import com.gaoyg.monkeyzicloud.provider.vo.MenuVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/9/2 19:32
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ApiModel(value = "权限树")
public class BindAuthVo {
    /**
     * 包含按钮权限和菜单权限
     */
    private List<MenuVo> authTree;
    /**
     * 该角色含有的权限ID
     */
    private List<Long> checkedAuthList;
}
