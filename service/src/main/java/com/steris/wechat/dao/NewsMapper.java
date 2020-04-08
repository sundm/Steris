package com.steris.wechat.dao;

import com.steris.wechat.entity.News;
import com.steris.wechat.entity.NewsExample;
import java.util.List;

public interface NewsMapper {
    int countByExample(NewsExample example);

    int insert(News record);

    int insertSelective(News record);

    List<News> selectByExample(NewsExample example);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
}