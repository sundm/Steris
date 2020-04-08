package com.steris.wechat.controller;

import com.steris.wechat.config.CodeMsgConfig;
import com.steris.wechat.dto.BasicResp;
import com.steris.wechat.dto.request.AdminReq;
import com.steris.wechat.dto.response.AdminResp;
import com.steris.wechat.entity.Admin;
import com.steris.wechat.service.AdminService;
import com.steris.wechat.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private MailService mailService;
    @Autowired
    private CodeMsgConfig codeMsgConfig;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @PostMapping("login")
    public BasicResp login(@RequestBody AdminReq req,HttpServletRequest request){
        List<Admin> admins = adminService.selectByExample(req);
        if (admins.size()==0){
            log.error("Get Login SearchNull.");
            return codeMsgConfig.searchNull();
        }
        AdminResp resp = new AdminResp();
        resp.setAdmin(admins.get(0));
        request.getSession().setAttribute("user",resp.getAdmin());
        log.info("Search Login Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }
    @RequestMapping("/logout")
    @ResponseBody
    public BasicResp logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return codeMsgConfig.success();
    }
    @PostMapping("send")
    public BasicResp send(){
        String to = "yuchen_z@163.com";
        String subject = "有新的用户,请尽快审核";
        String content = "有新的用户,请尽快审核";
        mailService.sendSimpleMail(to,subject,content);
        return codeMsgConfig.success();
    }
}
