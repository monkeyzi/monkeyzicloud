package com.gaoyg.monkeyzicloud.util.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/9/6 22:28
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Collections3 {
    /**
     * 返回两个集合的交集
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public static <T> List<T> intersection(Collection<T> a,Collection<T> b){
        List<T> list = new ArrayList<>();
        a.stream().forEach(aa->{
            if (b.contains(aa)){
                list.add(aa);
            }
        });
        return list;
    }
}
