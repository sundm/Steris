package com.steris.wechat.controller;

import com.steris.wechat.config.CodeMsgConfig;
import com.steris.wechat.dto.BasicResp;
import com.steris.wechat.dto.request.NewsReq;
import com.steris.wechat.dto.response.NewsResp;
import com.steris.wechat.entity.Home;
import com.steris.wechat.entity.NewsHead;
import com.steris.wechat.service.HomeService;
import com.steris.wechat.service.NewsService;
import com.steris.wechat.util.Constants;
import com.steris.wechat.util.JsonUtil;
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
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private HomeService homeService;
    @Autowired
    private CodeMsgConfig codeMsgConfig;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @PostMapping("add")
    public BasicResp add(@RequestBody NewsReq req){
        if (req.getHtml() == null || req.getHtml().equals("")){
            log.warn("Missing Field.");
            return codeMsgConfig.missingField();
        }
        int result = newsService.add(req);
        if (result == Constants.INSERT_ERROR)
            return codeMsgConfig.insertError();
        log.info("Add News Success.");
        return codeMsgConfig.success();
    }

    @PostMapping("getHead")
    public BasicResp getHead(@RequestBody NewsReq req){
        List<NewsHead> headInfo = newsService.searchHead(req);
        if (headInfo==null){
            log.error("GetHead SearchNull.");
            return codeMsgConfig.searchNull();
        }
        NewsResp resp = new NewsResp();
        resp.setHeadInfo(headInfo);
        log.info("Search GetHead Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }

    @PostMapping("getNews")
    public BasicResp getNews(@RequestBody NewsReq req){
        NewsResp resp = newsService.searchNews(req);
        if (resp==null){
            log.error("GetHead SearchNull.");
            return codeMsgConfig.searchNull();
        }
        log.info("Search GetHead Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }

    @PostMapping("modify")
    public BasicResp modify(@RequestBody NewsReq req){
        if (req.getId()==null || req.getId().equals("")){
            log.warn("Missing Field.");
            return codeMsgConfig.missingField();
        }
        int result = newsService.modify(req);
        if (result == Constants.UPDATE_ERROR)
            return codeMsgConfig.updateError();
        log.info("Modify News Success.");
        return codeMsgConfig.success();
    }
    /*
    * Start 首页大图管理
    * */
    @PostMapping("getHome")
    public BasicResp getHome(@RequestBody NewsReq req){
        List<Home> homeList = homeService.query(req);
        if (homeList==null){
            log.error("GetHome SearchNull.");
            return codeMsgConfig.searchNull();
        }
        NewsResp resp = new NewsResp();
        resp.setHomes(homeList);
        log.info("Search GetHome Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }
    @PostMapping("addHome")
    public BasicResp addHome(@RequestBody NewsReq req){
        if (req.getId()==null || req.getId().equals("") || req.getNewsTitle()==null
            || req.getNewsTitle().equals("") || req.getNewsImg()==null || req.getNewsImg().equals("")){
            log.warn("Missing Field.");
            return codeMsgConfig.missingField();
        }
        int result = homeService.add(req);
        if (result == Constants.INSERT_ERROR)
            return codeMsgConfig.insertError();
        log.info("Add Home Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("removeHome")
    public BasicResp removeHome(@RequestBody NewsReq req){
        if (req.getId()==null || req.getId().equals("")){
            log.warn("Missing Field.");
            return codeMsgConfig.missingField();
        }
        int result = homeService.delete(req.getId());
        if (result == Constants.UPDATE_ERROR)
            return codeMsgConfig.updateError();
        log.info("Delete Home Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("modifyHome")
    public BasicResp modifyHome(@RequestBody NewsReq req){
        if (req.getId()==null || req.getId().equals("")){
            log.warn("Missing Field.");
            return codeMsgConfig.missingField();
        }
        int result = homeService.modify(req);
        if (result == Constants.UPDATE_ERROR)
            return codeMsgConfig.updateError();
        log.info("Modify Home Success.");
        return codeMsgConfig.success();
    }
    /*
     * End 首页大图管理
     * */
    @PostMapping("remove")
    public BasicResp remove(@RequestBody NewsReq req){
        if (req.getId()==null || req.getId().equals("")){
            log.warn("Missing Field.");
            return codeMsgConfig.missingField();
        }
        int result = newsService.remove(req);
        if (result == Constants.UPDATE_ERROR)
            return codeMsgConfig.updateError();
        log.info("Delete NewsHead Success.");
        return codeMsgConfig.success();
    }

    @PostMapping("uploadToFile")
    @ResponseBody
    public String uploadToUser(@RequestParam("files") MultipartFile[] files, HttpServletRequest req, Model model) {
        String filePath = null;
        for (MultipartFile file:files){
            filePath = UtilFile.upload(file,Constants.FILE_UPLOAD);
            System.out.println(filePath);
        }
        return filePath; // 返回文件地址
    }

    @PostMapping("uploadHtml")
    @ResponseBody
    public String uploadHtml(@RequestBody NewsReq req) {
        String filePath = UtilFile.saveHtml(req.getHtml(),Constants.HTML_UPLOAD);
            System.out.println(filePath);
        return filePath; // 返回文件地址
    }

}
