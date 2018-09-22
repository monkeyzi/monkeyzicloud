package com.gaoyg.monkeyzicloud.provider.enums;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/9/22 21:08
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public enum  UcloudGroupStatusEnum {
    /**
     * 启用
     */
    ENABLE(0, "启用"),
    /**
     * 禁用
     */
    DISABLE(1, "禁用");

    /**
     * The Status.
     */
    int status;
    /**
     * The Value.
     */
    String value;

    /**
     * Gets name.
     *
     * @param status the status
     *
     * @return the name
     */
    public static String getName(int status) {
        for (UcloudGroupStatusEnum ele : UcloudGroupStatusEnum.values()) {
            if (status == ele.getStatus()) {
                return ele.getValue();
            }
        }
        return null;
    }

    UcloudGroupStatusEnum(int status, String value) {
        this.status = status;
        this.value = value;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    private static List<Integer> getStatusList() {
        List<Integer> list = Lists.newArrayList();
        for (UcloudGroupStatusEnum ele : UcloudGroupStatusEnum.values()) {
            list.add(ele.getStatus());
        }
        return list;
    }

    /**
     * Contains boolean.
     *
     * @param status the status
     *
     * @return the boolean
     */
    public static boolean contains(Integer status) {
        List<Integer> statusList = getStatusList();
        return statusList.contains(status);
    }
}
