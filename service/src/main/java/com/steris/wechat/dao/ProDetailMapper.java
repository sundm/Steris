package com.steris.wechat.dao;

import com.steris.wechat.entity.ProDetail;
import com.steris.wechat.entity.ProDetailExample;
import java.util.List;

public interface ProDetailMapper {
    int countByExample(ProDetailExample example);

    int insert(ProDetail record);

    int insertSelective(ProDetail record);

    List<ProDetail> selectByExample(ProDetailExample example);

    ProDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProDetail record);

    int updateByPrimaryKey(ProDetail record);
}