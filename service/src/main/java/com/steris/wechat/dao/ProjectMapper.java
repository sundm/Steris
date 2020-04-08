package com.steris.wechat.dao;

import com.steris.wechat.entity.Project;
import com.steris.wechat.entity.ProjectExample;
import java.util.List;

public interface ProjectMapper {
    int countByExample(ProjectExample example);

    int insert(Project record);

    int insertSelective(Project record);

    List<Project> selectByExample(ProjectExample example);

    Project selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
}