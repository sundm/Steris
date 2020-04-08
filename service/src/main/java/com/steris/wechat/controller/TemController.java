package com.steris.wechat.controller;

import com.steris.wechat.config.CodeMsgConfig;
import com.steris.wechat.dto.BasicResp;
import com.steris.wechat.dto.request.ProductReq;
import com.steris.wechat.dto.request.TemReq;
import com.steris.wechat.dto.response.ProductResp;
import com.steris.wechat.entity.Tem;
import com.steris.wechat.service.TemService;
import com.steris.wechat.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tem")
public class TemController {
    @Autowired
    private TemService temService;
    @Autowired
    private CodeMsgConfig codeMsgConfig;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @PostMapping("get")
    public BasicResp get(@RequestBody TemReq req){
        List<Tem> list = temService.query(req);
        if (list==null){
            log.error("Tem SearchNull With Get Tem.");
            return codeMsgConfig.searchNull();
        }
        ProductResp resp = new ProductResp();
        resp.setTemp(list);
        log.info("Search Tem Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }
    @PostMapping("getById")
    public BasicResp getById(@RequestBody TemReq req){
        ProductResp resp = temService.queryById(req);
        if (resp==null){
            log.error("Tem SearchNull With GetById Tem.");
            return codeMsgConfig.searchNull();
        }
        log.info("GetById Tem Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }
    @PostMapping("add")
    public BasicResp add(@RequestBody TemReq req){
        if (req.getName()==null || req.getName().equals("")
                || req.getUrl()==null || req.getUrl().equals("")
                || req.getState()==null || req.getState().equals("")){
            log.warn("Missing Field With Add Tem.");
            return codeMsgConfig.missingField();
        }
        int result = temService.add(req);
        if (result == Constants.INSERT_ERROR)
            return codeMsgConfig.insertError();
        log.info("Add Tem Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("remove")
    public BasicResp remove(@RequestBody TemReq req){
        if (req.getId()==null || req.getId().equals("")){
            log.warn("Missing Field With Remove Tem.");
            return codeMsgConfig.missingField();
        }
        int result = temService.delete(req.getId());
        if (result == Constants.UPDATE_ERROR)
            return codeMsgConfig.updateError();
        log.info("Delete Tem Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("modify")
    public BasicResp modify(@RequestBody TemReq req){
        if (req.getId()==null || req.getId().equals("")){
            log.warn("Missing Field With Modify Tem.");
            return codeMsgConfig.missingField();
        }
        int result = temService.modify(req);
        if (result == Constants.UPDATE_ERROR)
            return codeMsgConfig.updateError();
        log.info("Modify Tem Success.");
        return codeMsgConfig.success();
    }
}
