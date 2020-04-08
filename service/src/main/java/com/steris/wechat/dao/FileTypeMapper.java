package com.steris.wechat.dao;

import com.steris.wechat.entity.FileType;
import com.steris.wechat.entity.FileTypeExample;
import java.util.List;

public interface FileTypeMapper {
    int countByExample(FileTypeExample example);

    int insert(FileType record);

    int insertSelective(FileType record);

    List<FileType> selectByExample(FileTypeExample example);

    FileType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileType record);

    int updateByPrimaryKey(FileType record);
}