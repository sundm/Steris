package com.steris.wechat.dao;

import com.steris.wechat.entity.ProType;
import com.steris.wechat.entity.ProTypeExample;
import java.util.List;

public interface ProTypeMapper {
    int countByExample(ProTypeExample example);

    int insert(ProType record);

    int insertSelective(ProType record);

    List<ProType> selectByExample(ProTypeExample example);

    ProType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProType record);

    int updateByPrimaryKey(ProType record);
}