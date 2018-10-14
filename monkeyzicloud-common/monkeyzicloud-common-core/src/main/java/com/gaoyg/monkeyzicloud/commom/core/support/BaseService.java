package com.gaoyg.monkeyzicloud.commom.core.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: 高yg
 * @date: 2018/8/5 08:22
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
public abstract class BaseService<T> implements IBaseService<T> {

    @Autowired
    protected Mapper<T> mapper;
    /**
     * Gets mapper.
     * @return the mapper
     */
    public Mapper<T> getMapper() {
        return mapper;
    }


    @Override
    public List<T> select(T record) {
        return mapper.select(record);
    }

    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public T selectOne(T record) {
        return mapper.selectOne(record);
    }

    @Override
    public int selectCount(T record) {
        return mapper.selectCount(record);
    }

    @Override
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    @Override
    public int insert(T record) {
        return mapper.insert(record);
    }

    @Override
    public int update(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int save(T record) {
        return mapper.insertSelective(record);
    }

    @Override
    public int selectCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }

    @Override
    public int delete(T record) {
        return mapper.delete(record);
    }

}
