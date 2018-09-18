package com.gaoyg.monkeyzicloud.enums;

import lombok.Getter;

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
    UCLOUD10012008(10012008, "找不到绑定的角色, roleId=%s"),
    UCLOUD10012009(10012009, "该角色编码已存在"),
    UCLOUD10012010(10012010, "该角色Id不存在"),
    UCLOUD10012011(10012011, "找不到角色,roleId=%s"),
    UCLOUD10012002(10012002, "该角色不允许禁用"),
    UCLOUD100120012(10012004,"超级管理员角色Id不能为空"),

    UCLOUD10012004(10012004, "用户名不能为空"),
    UCLOUD10012005(10012005, "密码不能为空"),
    UCLOUD10011026(10011026, "更新用户失败, userId=%"),
    UCLOUD10011025(10011025, "用户已存在, loginName=%"),
    UCLOUD10011027(10011027, "姓名已存在, userName=%"),
    UCLOUD10011028(10011028, "邮箱已存在, email=%"),
    UCLOUD10011001(10011001, "用户Id不能为空,userId=%"),
    UCLOUD10011023(10011023, "越权操作"),
    UCLOUD10011011(10011011, "用户不存在, userId=%s"),
    UCLOUD10011024(10011024, "找不到绑定的用户, userId=%"),

    UCLOUD10011007(10011007, "登录名不能为空"),
    UCLOUD10011006(10011006, "原密码不能为空"),
    UCLOUD10011008(10011008, "新密码不能为空"),
    UCLOUD10011009(10011009, "确认密码不能为空"),
    UCLOUD10011010(10011010, "两次密码不一致"),
    UCLOUD10011035(10011035, "原始密码输入错误"),
    UCLOUD10011036(10011036, "新密码和原始密码不能相同"),
    UCLOUD10013009(10013009, "请先分配菜单"),
    UCLOUD10013001(10013001, "组织不能为空"),
    /**
     * 菜单
     */
    UCLOUD10014001(10014001, "菜单Id不能为空"),
    UCLOUD10014002(10014002, "菜单不存在,menuId=%s"),
    UCLOUD10014003(10014003, "根菜单不能禁用"),
    UCLOUD10014004(10014004, "禁用菜单失败,menuId=%s"),
    UCLOUD10014005(10014005, "禁用菜单失败,menuId=%s"),
    UCLOUD10014006(10014006, "更新菜单状态失败,menuId=%s"),
    UCLOUD10014007(10014007, "父菜单不存在,menuId=%s"),
    UCLOUD10014008(10014008, "更新上级菜单失败,menuId=%s"),
    UCLOUD10014009(10014009, "该菜单下有子菜单，请先删除子菜单"),
    UCLOUD10014010(10014010, "菜单Pid不能为空"),
    UCLOUD10014011(10014011, "删除菜单失败, menuId=%s"),
    /**
     * 权限
     */
    UCLOUD10015001(10015001, "权限不存在,id=%s"),
    UCLOUD10015002(10015002, "找不到权限信息, actionId=%s"),
    UCLOUD10015003(10015003, "删除失败, actionId=%s"),
    UCLOUD10015004(10015004, "删除权限Id不能为空");

    private String msg;
    private Integer code;
    ErrorCodeEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
