package com.steris.wechat.service.impl;

import com.steris.wechat.dao.TemMapper;
import com.steris.wechat.dao.sql.FileDAO;
import com.steris.wechat.dto.request.ProductReq;
import com.steris.wechat.dto.request.TemReq;
import com.steris.wechat.dto.response.ProductResp;
import com.steris.wechat.entity.Tem;
import com.steris.wechat.entity.TemExample;
import com.steris.wechat.service.TemService;
import com.steris.wechat.util.Constants;
import com.steris.wechat.util.JsonUtil;
import com.steris.wechat.util.UtilFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class TemServiceImpl implements TemService {
    @Autowired
    private TemMapper temMapper;
    @Autowired
    private FileDAO fileDAO;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    @Transactional
    public int add(TemReq req) {
        try {
            String top = "<html lang='en'><head><meta charset='utf-8'></head><body>";
            String foot = "</body></html>";
            String filePath = UtilFile.saveHtml((top + req.getUrl() + foot),Constants.HTML_UPLOAD);
            if (filePath.equals("error")){
                logger.error("Tem Add error.");
                return Constants.INSERT_ERROR;
            }

            Tem tem = new Tem();
            tem.setName(req.getName());
            tem.setState(req.getState());
            tem.setUrl(filePath);
            int result = temMapper.insertSelective(tem);
            if (result != Constants.ADD_SUCESS){
                logger.error("Tem Add error.");
                return Constants.INSERT_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Tem Add error.",e);
            return Constants.INSERT_ERROR;
        }
    }

    @Override
    public List<Tem> query(TemReq req) {
        try {
            TemExample example = new TemExample();
            if (req.getState()!=null){
                example.createCriteria().andStateEqualTo(req.getState());
            }
            List<Tem> list = temMapper.selectByExample(example);
            return list;
        }catch (Exception e){
            logger.error("Query Tem Is Error.",e);
            return null;
        }
    }

    @Override
    public ProductResp queryById(TemReq req) {
        try {
            ProductResp resp = new ProductResp();
            Tem list = temMapper.selectByPrimaryKey(req.getId());
            String path =  Constants.HTML_UPLOAD + list.getUrl();
            String html = JsonUtil.readJsonFile(path);
            resp.setTem(list);
            resp.setHtml(html);
            return resp;
        }catch (Exception e){
            logger.error("QueryById Tem Is Error.",e);
            return null;
        }
    }

    @Override
    @Transactional
    public int modify(TemReq req) {
        try {
            Tem tem = new Tem();
            tem.setId(req.getId());
            if (req.getUrl()!=null && !req.getUrl().equals("")){
                Tem list = temMapper.selectByPrimaryKey(req.getId());
                UtilFile.deleteHtml(Constants.HTML_UPLOAD,list.getUrl());
                String top = "<html lang='en'><head><meta charset='utf-8'></head><body>";
                String foot = "</body></html>";
                String filePath =UtilFile.saveHtml((top + req.getUrl() + foot),Constants.HTML_UPLOAD);
                if (filePath.equals("error")){
                    logger.error("Tem Update error.");
                    return Constants.UPDATE_ERROR;
                }
                tem.setUrl(filePath);
            }
            if (req.getName()!=null){
                tem.setName(req.getName());
            }
            int result = temMapper.updateByPrimaryKeySelective(tem);
            if (result != Constants.ADD_SUCESS){
                logger.error("Tem modify error.");
                return Constants.UPDATE_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Tem modify error.",e);
            return Constants.UPDATE_ERROR;
        }
    }

    @Override
    @Transactional
    public int delete(Integer id) {
        try {
            Tem list = temMapper.selectByPrimaryKey(id);
            Boolean flag = UtilFile.delFile(Constants.HTML_UPLOAD + list.getUrl());
            if (!flag){
                logger.error("Tem delete error.");
                return Constants.UPDATE_ERROR;
            }
            int result = fileDAO.deleteTemById(id);
            if (result != Constants.ADD_SUCESS){
                logger.error("Tem delete error.");
                return Constants.UPDATE_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Tem Remove error.",e);
            return Constants.UPDATE_ERROR;
        }
    }
}
