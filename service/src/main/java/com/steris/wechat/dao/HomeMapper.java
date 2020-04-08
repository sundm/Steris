package com.steris.wechat.dao;

import com.steris.wechat.entity.Home;
import com.steris.wechat.entity.HomeExample;
import java.util.List;

public interface HomeMapper {
    int countByExample(HomeExample example);

    int insert(Home record);

    int insertSelective(Home record);

    List<Home> selectByExample(HomeExample example);

    Home selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Home record);

    int updateByPrimaryKey(Home record);
}