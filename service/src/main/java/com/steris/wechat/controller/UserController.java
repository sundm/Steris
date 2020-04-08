package com.steris.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.steris.wechat.config.CodeMsgConfig;
import com.steris.wechat.dto.BasicResp;
import com.steris.wechat.dto.request.UserInfoReq;
import com.steris.wechat.dto.response.UserInfoResp;
import com.steris.wechat.entity.Mail;
import com.steris.wechat.entity.User;
import com.steris.wechat.service.MailService;
import com.steris.wechat.service.UserService;
import com.steris.wechat.service.impl.MailServiceImpl;
import com.steris.wechat.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    @Autowired
    private CodeMsgConfig codeMsgConfig;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @PostMapping("/auth")
    public BasicResp auth(@RequestBody UserInfoReq req) {
        if (req.getOpenId() == null){
            log.warn("Missing Field");
            return codeMsgConfig.missingField();
        }
        User user = userService.selectByPrimaryKey(req);
        if (user==null){
            log.error("User SearchNull.");
            return codeMsgConfig.searchNull();
        }
        UserInfoResp resp = new UserInfoResp();
        resp.setState(user.getState());
        resp.setUserInfo(user);
        log.info("Search userInfo Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }

    @PostMapping("addUser")
    public BasicResp addUser(@RequestBody UserInfoReq req){
        if (req.getOpenId() == null || req.getOpenId().equals("")
                || req.getJob() == null || req.getJob().equals("")
                || req.getPhone() == null || req.getPhone().equals("")){
            log.warn("Missing Field.");
            return codeMsgConfig.missingField();
        }
        int result = userService.addUser(req);
        if (result == Constants.INSERT_ERROR){
            log.warn("Search User Is Error.");
            return codeMsgConfig.insertError();
        }
        List<Mail> mail = userService.getMail(req);
        for (Mail list : mail){
            String to = list.getName();
            String subject = "有新的WINHOME会员加入申请,请尽快审核";
            String content = "有新的WINHOME会员加入申请,请尽快审核";
            mailService.sendSimpleMail(to,subject,content);
        }
        log.info("Search userInfo Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("modify")
    public BasicResp modifyUser(@RequestBody UserInfoReq req){
        if (req.getOpenId() == null){
            log.warn("Missing Modify User Field.");
            return codeMsgConfig.missingField();
        }
        int result = userService.modifyUser(req);
        if (result == Constants.UPDATE_ERROR){
            log.warn("Modify User Is Error.");
            return codeMsgConfig.updateError();
        }
        log.info("Modify User Success.");
        return codeMsgConfig.success();
    }

    @PostMapping("remove")
    public BasicResp removeHome(@RequestBody UserInfoReq req){
        if (req.getOpenId()==null || req.getOpenId().equals("")){
            log.warn("Missing Field.");
            return codeMsgConfig.missingField();
        }
        int result = userService.remove(req.getOpenId());
        if (result == Constants.UPDATE_ERROR)
            return codeMsgConfig.updateError();
        log.info("Remove User Success.");
        return codeMsgConfig.success();
    }

    @PostMapping("getUser")
    @ResponseBody
    public BasicResp getUser(@RequestBody UserInfoReq req){
        List<User> userList = userService.auth(req);
        if (userList==null){
            log.error("User SearchNull.");
            return codeMsgConfig.searchNull();
        }
        UserInfoResp resp = new UserInfoResp();
        resp.setUserList(userList);
        log.info("Search userInfo Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }

    @PostMapping("getMail")
    @ResponseBody
    public BasicResp getMail(@RequestBody UserInfoReq req){
        List<Mail> mail = userService.getMail(req);
        if (mail==null){
            log.error("Mail SearchNull.");
            return codeMsgConfig.searchNull();
        }
        UserInfoResp resp = new UserInfoResp();
        resp.setMail(mail);
        log.info("Search Mail Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }
    @PostMapping("deleteMail")
    public BasicResp deleteMail(@RequestBody UserInfoReq req){
        if (req.getRId() == null){
            log.warn("Missing Modify Mail Field.");
            return codeMsgConfig.missingField();
        }
        int result = userService.delete(req.getRId());
        if (result == Constants.UPDATE_ERROR){
            log.warn("Modify Mail Is Error.");
            return codeMsgConfig.updateError();
        }
        log.info("Modify Mail Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("addMail")
    public BasicResp addMail(@RequestBody UserInfoReq req){
        if (req.getMailName() == null){
            log.warn("Missing Field.");
            return codeMsgConfig.missingField();
        }
        int result = userService.add(req);
        if (result == Constants.INSERT_ERROR){
            log.warn("Search Mail Is Error.");
            return codeMsgConfig.insertError();
        }
        log.info("Search Mail Success.");
        return codeMsgConfig.success();
    }
}
