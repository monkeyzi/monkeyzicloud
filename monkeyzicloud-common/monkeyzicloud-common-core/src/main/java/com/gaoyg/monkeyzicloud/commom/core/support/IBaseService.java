package com.gaoyg.monkeyzicloud.commom.core.support;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/5 08:15
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface IBaseService<T> {
    /**
     * 根据属性值查询
     * @param record
     * @return
     */
   List<T> select(T record);

    /**
     * 根据主键查询
     * @param key
     * @return
     */
   T selectByKey(Object key);

    /**
     * 查询全部结果，使用select(null)也能达到同样的结果
     * @return
     */
   List<T> selectAll();


    /**
     * 根据实体中的属性进行查询, 只能有一个返回值, 有多个结果是抛出异常,
     * @param record
     * @return
     */
    T selectOne(T record);

    /**
     *根据实体中的属性查询总数
     * @param record
     * @return
     */
    int selectCount(T record);

    /**
     * 根据实体的属性值保存，null值不保存，会使用数据库默认值
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * 根据属性值保存，null也会保存
     * @param record
     * @return
     */
    int insert(T record);
}
