package com.steris.wechat.dto.response;

import com.steris.wechat.dto.BasicResp;
import com.steris.wechat.entity.Project;
import lombok.Data;

import java.util.List;

@Data
public class ProjectResp  extends BasicResp {
    private Project project;
    private List<Project> listInfo;
}
