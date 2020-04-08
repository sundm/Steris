package com.steris.wechat.dao;

import com.steris.wechat.entity.Files;
import com.steris.wechat.entity.FilesExample;
import java.util.List;

public interface FilesMapper {
    int countByExample(FilesExample example);

    int insert(Files record);

    int insertSelective(Files record);

    List<Files> selectByExample(FilesExample example);

    Files selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Files record);

    int updateByPrimaryKey(Files record);
}