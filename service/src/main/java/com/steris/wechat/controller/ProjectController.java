package com.steris.wechat.controller;

import com.steris.wechat.config.CodeMsgConfig;
import com.steris.wechat.dto.BasicResp;
import com.steris.wechat.dto.request.ProjectReq;
import com.steris.wechat.dto.response.ProjectResp;
import com.steris.wechat.entity.Project;
import com.steris.wechat.service.ProjectService;
import com.steris.wechat.util.Constants;
import com.steris.wechat.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CodeMsgConfig codeMsgConfig;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @PostMapping("get")
    public BasicResp get(@RequestBody ProjectReq req){
        List<Project> list = projectService.search(req);
        if (list==null){
            log.error("Project SearchNull With Get Project.");
            return codeMsgConfig.searchNull();
        }
        ProjectResp resp = new ProjectResp();
        resp.setListInfo(list);
        log.info("Search Project Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }
    @PostMapping("getById")
    public BasicResp getById(@RequestBody ProjectReq req){
        if (req.getId()==null || req.getId().equals("")){
            log.warn("Missing Field With getById Project.");
            return codeMsgConfig.missingField();
        }
        Project project = projectService.selectByPrimaryKey(req);
        ProjectResp resp = new ProjectResp();
        resp.setProject(project);
        if (resp==null){
            log.error("Project SearchNull With GetById Project.");
            return codeMsgConfig.searchNull();
        }
        log.info("GetById Project Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }
    @PostMapping("add")
    public BasicResp add(@RequestBody ProjectReq req){
        if (req.getUId()==null || req.getUId().equals("")){
            log.warn("Missing Field With Add Project.");
            return codeMsgConfig.missingField();
        }
        int result = projectService.add(req);
        if (result == Constants.INSERT_ERROR)
            return codeMsgConfig.insertError();
        log.info("Add Project Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("remove")
    public BasicResp remove(@RequestBody ProjectReq req){
        if (req.getId()==null || req.getId().equals("")){
            log.warn("Missing Field With Remove Project.");
            return codeMsgConfig.missingField();
        }
        int result = projectService.remove(req.getId());
        if (result == Constants.UPDATE_ERROR)
            return codeMsgConfig.updateError();
        log.info("Delete Project Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("modify")
    public BasicResp modify(@RequestBody ProjectReq req){
        if (req.getId()==null || req.getId().equals("")){
            log.warn("Missing Field With Modify Project.");
            return codeMsgConfig.missingField();
        }
        int result = projectService.modify(req);
        if (result == Constants.UPDATE_ERROR)
            return codeMsgConfig.updateError();
        log.info("Modify Project Success.");
        return codeMsgConfig.success();
    }
    @RequestMapping(value = "/excel",method = RequestMethod.GET)
    public void ExportBankCkeckInfo(HttpServletResponse response, HttpServletRequest request){
        //得到所有要导出的数据
        ProjectReq req = new ProjectReq();
        req.setRedu(request.getParameter("name"));
        List<Project> list = projectService.search(req);
        //定义导出的excel名字
        String excelName = "项目列表";

        //获取需要转出的excel表头的map字段
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("id","编号");
        fieldMap.put("redu","姓名");
        fieldMap.put("name","项目名");
        fieldMap.put("pName","产品名称");
        fieldMap.put("address","所属地区");
        fieldMap.put("hosp","医院名称");
        fieldMap.put("day","预计完成时间");
        fieldMap.put("state","项目详情");

        //导出用户相关信息
        new ExcelUtil().export(excelName,list,fieldMap,response);
    }
}
