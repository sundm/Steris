package com.steris.wechat.service.impl;

import com.steris.wechat.dao.NewsContentMapper;
import com.steris.wechat.dao.NewsHeadMapper;
import com.steris.wechat.dao.NewsImgMapper;
import com.steris.wechat.dao.NewsMapper;
import com.steris.wechat.dao.sql.FileDAO;
import com.steris.wechat.dao.sql.NewsDAO;
import com.steris.wechat.dto.request.NewsReq;
import com.steris.wechat.dto.response.NewsResp;
import com.steris.wechat.entity.*;
import com.steris.wechat.entity.info.NewAdd;
import com.steris.wechat.entity.info.NewsInfo;
import com.steris.wechat.service.NewsService;
import com.steris.wechat.util.Constants;
import com.steris.wechat.util.JsonUtil;
import com.steris.wechat.util.UtilFile;
import lombok.experimental.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private FileDAO fileDAO;
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private NewsContentMapper contentMapper;
    @Autowired
    private NewsImgMapper imgMapper;
    @Autowired
    private NewsHeadMapper headMapper;
    @Autowired
    private NewsDAO newsDAO;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /*
    * 添加会员活动 及 详情
    * */
    @Override
    @Transactional
    public int add(NewsReq req) {
        try {
            String top = "<html lang='en'><head><meta charset='utf-8'></head><body>";
            String foot = "</body></html>";
            String filePath =UtilFile.saveHtml((top + req.getHtml() + foot),Constants.HTML_UPLOAD);
            if (filePath.equals("error")){
                logger.error("NewsHead Add error.");
                return Constants.INSERT_ERROR;
            }
            NewsHead head = new NewsHead();
            head.setnImg(Constants.FILE_IMG_PATH + req.getNewsImg());
            head.setnContent(req.getNewsText());
            head.setnTitle(req.getNewsTitle());
            head.setCreateTime(new Date());
            head.setnSeq(1);
            head.setState("2");
            head.setDetail(filePath);
            int headResult = headMapper.insertSelective(head);
            if (headResult != Constants.ADD_SUCESS){
                logger.error("NewsHead Add error.");
                return Constants.INSERT_ERROR;
            }
            /*News news = new News();
            for (NewAdd newsInfo: req.getNews()) {
                if (newsInfo.getType().equals(Constants.ADD_NEWS_IMG)){
                    news.setImg(Constants.FILE_IMG_PATH+newsInfo.getText());
                }else if (newsInfo.getType().equals(Constants.ADD_NEWS_TEXT)){
                    news.setImg(null);
                    news.setContent(newsInfo.getText());
                }else {
                    logger.error("News Add error.");
                    return Constants.INSERT_ERROR;
                }
                news.sethId(head.getId());
                news.setType(newsInfo.getType());
                news.setNum(newsInfo.getSeq());
                int result = newsMapper.insertSelective(news);
                if (result != Constants.ADD_SUCESS){
                    logger.error("NewsHead Add error.");
                    return Constants.INSERT_ERROR;
                }
            }*/
            return Constants.ADD_SUCESS;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("News Add error.",e);
            return Constants.INSERT_ERROR;
        }
    }
    /*
    * 修改活动内容
    * */
    @Override
    @Transactional
    public int modify(NewsReq req) {
        try {
            NewsHead head = new NewsHead();
            if (req.getId()!=null)
                head.setId(req.getId());
            if (req.getState()!=null){
                if (req.getState().equals("1"))
                    head.setState("2");
                else if (req.getState().equals("2"))
                    head.setState("1");
            }
            if (req.getNewsImg()!=null)
                head.setnImg(Constants.FILE_IMG_PATH + req.getNewsImg());
            if(req.getNewsTitle()!=null)
                head.setnTitle(req.getNewsTitle());
            if (req.getNewsText()!=null)
                head.setnContent(req.getNewsText());
            if (req.getHtml()!=null && !req.getHtml().equals("")){
                NewsHead newsHead = headMapper.selectByPrimaryKey(head.getId());
                UtilFile.deleteHtml(Constants.HTML_UPLOAD,newsHead.getDetail());
                String top = "<html lang='en'><head><meta charset='utf-8'></head><body>";
                String foot = "</body></html>";
                String filePath =UtilFile.saveHtml((top + req.getHtml() + foot),Constants.HTML_UPLOAD);
                if (filePath.equals("error")){
                    logger.error("NewsHead Update error.");
                    return Constants.UPDATE_ERROR;
                }
                head.setDetail(filePath);
            }
            int headResult = headMapper.updateByPrimaryKeySelective(head);
            if (headResult <= 0){
                logger.error("News Modify error.");
                return Constants.UPDATE_ERROR;
            }
            return 1;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("News Modify error.",e);
            return Constants.UPDATE_ERROR;
        }
    }

    /*
    * 获取活动简述列表
    * */
    @Override
    public List<NewsHead> searchHead(NewsReq req) {
        try {
            NewsHeadExample example = new NewsHeadExample();
            if (req.getState()!=null){
                example.createCriteria().andStateEqualTo(req.getState());
            }
            example.setOrderByClause("create_time DESC");
            List<NewsHead> head = headMapper.selectByExample(example);
            if (head == null){
                logger.error("SearchNewsHead Null");
                return null;
            }
            return head;
        }catch (Exception e){
            logger.error("SearchHead Is Error.",e);
            return null;
        }
    }

    /*
    * 删除活动信息
    * */
    @Override
    @Transactional
    public int remove(NewsReq req) {
        try {
            NewsHead head = headMapper.selectByPrimaryKey(req.getId());
            Boolean html = UtilFile.delFile(Constants.HTML_UPLOAD + head.getDetail());
            Boolean img = UtilFile.delFile(Constants.F_REMOVE + head.getnImg());
            if (!html || !img){
                logger.error("News Remove error.");
                return Constants.UPDATE_ERROR;
            }
            int result = fileDAO.deleteNewsById(req.getId());
            if (result != Constants.ADD_SUCESS){
                logger.error("News Remove error.");
                return Constants.UPDATE_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("News Remove error.",e);
            return Constants.UPDATE_ERROR;
        }
    }

    /*
    * 顺序获取活动详情信息列表
    * */
    @Override
    public NewsResp searchNews(NewsReq req) {
        try {
            NewsResp resp = new NewsResp();
            NewsHead head = headMapper.selectByPrimaryKey(req.getHeadId());
            if (head == null){
                logger.error("SearchNews Null");
                return null;
            }
            //String path = System.getProperty("user.dir")+ Constants.HTML_UPLOAD + head.getDetail();
            String path =  Constants.HTML_UPLOAD + head.getDetail();
            String html = JsonUtil.readJsonFile(path);
            resp.setHtml(html);
            resp.setHead(head);
            return resp;
        }catch (Exception e){
            logger.error("SearchNews Is Error.",e);
            return null;
        }
    }
}
