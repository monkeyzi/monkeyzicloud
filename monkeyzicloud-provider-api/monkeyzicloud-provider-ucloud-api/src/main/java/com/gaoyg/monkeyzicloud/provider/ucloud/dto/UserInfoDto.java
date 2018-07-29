package com.gaoyg.monkeyzicloud.provider.ucloud.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author: 高yg
 * @date: 2018/7/25 21:51
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@ToString
public class UserInfoDto {
    private Long id;
    private String userName;
    private String password;
    private String loginName;
}
