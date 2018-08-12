package com.gaoyg.monkeyzicloud.provider.ucloud.mapper;

import com.gaoyg.monkeyzicloud.commom.core.mybatis.MyMapper;
import com.gaoyg.monkeyzicloud.provider.ucloud.model.domain.UcloudUserToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author: 高yg
 * @date: 2018/8/12 14:49
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Mapper
@Component
public interface UcloudUserTokenMapper extends MyMapper<UcloudUserToken> {
    List<UcloudUserToken> selectTokenList(UcloudUserToken ucloudUserToken);
    int batchUpdateTokenOffLine(Map<String, Object> map);
    List<Long> listOffLineTokenId();
}
