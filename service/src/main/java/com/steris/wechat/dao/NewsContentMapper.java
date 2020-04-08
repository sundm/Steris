package com.steris.wechat.dao;

import com.steris.wechat.entity.NewsContent;
import com.steris.wechat.entity.NewsContentExample;
import java.util.List;

public interface NewsContentMapper {
    int countByExample(NewsContentExample example);

    int insert(NewsContent record);

    int insertSelective(NewsContent record);

    List<NewsContent> selectByExample(NewsContentExample example);

    NewsContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsContent record);

    int updateByPrimaryKey(NewsContent record);
}