package com.steris.wechat.service;

import com.steris.wechat.dto.request.ProjectReq;
import com.steris.wechat.entity.Project;

import java.util.List;

public interface ProjectService {
    int add(ProjectReq req);

    List<Project> search(ProjectReq req);

    Project selectByPrimaryKey(ProjectReq req);

    int modify(ProjectReq req);

    int remove(Integer id);
}
