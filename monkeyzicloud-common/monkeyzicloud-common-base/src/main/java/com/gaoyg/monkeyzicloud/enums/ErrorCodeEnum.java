package com.gaoyg.monkeyzicloud.enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: 高yg
 * @date: 2018/7/28 20:15
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Getter
public enum  ErrorCodeEnum {
    UCLOUD00001038(10011038, "登录状态失效,请重新登录"),
    UCLOUD00001039(10011039, "参数错误"),
    UCLOUD00001040(10011040, "解析header失败"),
    UCLOUD10011039(10011039, "验证token失败"),
    UCLOUD10012001(10012001, "角色ID不能为空"),
    UCLOUD10012003(10012003, "管理员角色不能删除"),
    UCLOUD10011002(10011002, "找不到用户,loginName=%s"),
    UCLOUD10012006(10012006, "删除角色失败, roleId=%s"),
    UCLOUD10012007(10012007, "批量删除角色失败, roleId=%s"),
    UCLOUD10012002(10012002, "该角色不允许禁用");
    private String msg;
    private Integer code;
    ErrorCodeEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
