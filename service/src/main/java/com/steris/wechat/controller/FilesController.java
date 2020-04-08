package com.steris.wechat.controller;

import com.steris.wechat.config.CodeMsgConfig;
import com.steris.wechat.dto.BasicResp;
import com.steris.wechat.dto.request.FileReq;
import com.steris.wechat.dto.response.FileResp;
import com.steris.wechat.entity.FileType;
import com.steris.wechat.entity.Files;
import com.steris.wechat.entity.info.FileInfo;
import com.steris.wechat.service.FileService;
import com.steris.wechat.util.Constants;
import com.steris.wechat.util.UtilFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/files")
public class FilesController {
    @Autowired
    private FileService fileService;
    @Autowired
    private CodeMsgConfig codeMsgConfig;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @PostMapping("add")
    public BasicResp add(@RequestBody FileReq req){
        if (req.getName() == null || req.getTypeId() == null
                || req.getState() == null || req.getUrl() == null || req.getConcent() == null){
            log.warn("Missing Field.");
            return codeMsgConfig.missingField();
        }
        int result = fileService.add(req);
        if (result == Constants.INSERT_ERROR)
            return codeMsgConfig.insertError();
        log.info("Add File Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("addType")
    public BasicResp addType(@RequestBody FileReq req){
        if (req.getTypeName() == null || req.getState() == null
                || req.getType() == null){
            log.warn("Missing Field.");
            return codeMsgConfig.missingField();
        }
        int result = fileService.addType(req);
        if (result == Constants.INSERT_ERROR)
            return codeMsgConfig.insertError();
        log.info("Add FileType Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("modify")
    public BasicResp modify(@RequestBody FileReq req){
        if (req.getId() == null){
            log.warn("Missing Field.");
            return codeMsgConfig.missingField();
        }
        int result = fileService.modify(req);
        if (result == Constants.UPDATE_ERROR)
            return codeMsgConfig.insertError();
        log.info("modify Files Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("modifyType")
    public BasicResp modifyType(@RequestBody FileReq req){
        if (req.getId() == null){
            log.warn("Missing Field.");
            return codeMsgConfig.missingField();
        }
        int result = fileService.modifyType(req);
        if (result == Constants.UPDATE_ERROR)
            return codeMsgConfig.insertError();
        log.info("modify FileType Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("remove")
    public BasicResp remove(@RequestBody FileReq req){
        if (req.getId() == null){
            log.warn("Missing Field.");
            return codeMsgConfig.missingField();
        }
        int result = fileService.remove(req);
        if (result == Constants.UPDATE_ERROR)
            return codeMsgConfig.updateError();
        log.info("remove Files Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("removeType")
    public BasicResp removeType(@RequestBody FileReq req){
        if (req.getId() == null){
            log.warn("Missing Field.");
            return codeMsgConfig.missingField();
        }
        int result = fileService.removeType(req);
        if (result == Constants.UPDATE_ERROR)
            return codeMsgConfig.updateError();
        log.info("remove FileType Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("getFile")
    public BasicResp getFile(@RequestBody FileReq req){
        List<FileInfo> info = fileService.query(req);
        if (info==null){
            log.error("getFile SearchNull.");
            return codeMsgConfig.searchNull();
        }
        FileResp resp = new FileResp();
        resp.setFileInfo(info);
        log.info("Search getFile Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }
    @PostMapping("byId")
    public BasicResp getFileById(@RequestBody FileReq req){
        Files info = fileService.search(req);
        if (info==null){
            log.error("getFileById SearchNull.");
            return codeMsgConfig.searchNull();
        }
        FileResp resp = new FileResp();
        resp.setFiles(info);
        log.info("Search getFileById Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }
    @PostMapping("getType")
    public BasicResp getType(@RequestBody FileReq req){
        List<FileType> info = fileService.searchType(req);
        if (info==null){
            log.error("getFile SearchNull.");
            return codeMsgConfig.searchNull();
        }
        FileResp resp = new FileResp();
        resp.setTypeInfo(info);
        log.info("Search getType Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }

    @PostMapping("uploadToFile")
    @ResponseBody
    public String uploadToUser(@RequestParam("files") MultipartFile[] files, HttpServletRequest req, Model model) {
        String filePath = null;
        for (MultipartFile file:files){
            filePath = UtilFile.upload(file,Constants.F_UPLOAD);
            System.out.println(filePath);
        }
        return filePath; // 返回文件地址
    }
}
