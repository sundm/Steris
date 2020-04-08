package com.steris.wechat.dao;

import com.steris.wechat.entity.NewsImg;
import com.steris.wechat.entity.NewsImgExample;
import java.util.List;

public interface NewsImgMapper {
    int countByExample(NewsImgExample example);

    int insert(NewsImg record);

    int insertSelective(NewsImg record);

    List<NewsImg> selectByExample(NewsImgExample example);

    NewsImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsImg record);

    int updateByPrimaryKey(NewsImg record);
}