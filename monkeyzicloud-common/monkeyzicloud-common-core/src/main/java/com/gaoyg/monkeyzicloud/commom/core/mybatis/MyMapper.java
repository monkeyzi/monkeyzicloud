package com.gaoyg.monkeyzicloud.commom.core.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author: 高yg
 * @date: 2018/7/31 23:08
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface MyMapper<T>  extends Mapper<T>,MySqlMapper<T> {
}
