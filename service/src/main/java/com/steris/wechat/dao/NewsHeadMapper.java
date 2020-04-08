package com.steris.wechat.dao;

import com.steris.wechat.entity.NewsHead;
import com.steris.wechat.entity.NewsHeadExample;
import java.util.List;

public interface NewsHeadMapper {
    int countByExample(NewsHeadExample example);

    int insert(NewsHead record);

    int insertSelective(NewsHead record);

    List<NewsHead> selectByExample(NewsHeadExample example);

    NewsHead selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsHead record);

    int updateByPrimaryKey(NewsHead record);
}