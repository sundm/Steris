package com.steris.wechat.dao;

import com.steris.wechat.entity.Tem;
import com.steris.wechat.entity.TemExample;
import java.util.List;

public interface TemMapper {
    int countByExample(TemExample example);

    int insert(Tem record);

    int insertSelective(Tem record);

    List<Tem> selectByExample(TemExample example);

    Tem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tem record);

    int updateByPrimaryKey(Tem record);
}