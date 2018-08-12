package com.gaoyg.monkeyzicloud.util.pubutils;

import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Map;

/**
 * @author: 高yg
 * @date: 2018/7/31 21:05
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:判断一个对象是否为空或者非空
 */
@NoArgsConstructor
public class PublicUtil {
    /**
     * obj is empty
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj){
        if (obj==null){
            return true;
        }
        if (obj==""){
            return true;
        }
        if (obj instanceof  String){
            return ((String) obj).length()==0;
        }
        if (obj instanceof Collection){
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map){
            return ((Map) obj).size()==0;
        }
        return false;
    }

    /**
     * 判断一个对象不为空
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj){
        return !isEmpty(obj);
    }
}
