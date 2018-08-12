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
    UCLOUD10011039(10011039, "验证token失败");
    private String msg;
    private Integer code;
    ErrorCodeEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
