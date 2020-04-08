package com.steris.wechat.service.impl;

import com.steris.wechat.dao.HomeMapper;
import com.steris.wechat.dao.sql.FileDAO;
import com.steris.wechat.dto.request.NewsReq;
import com.steris.wechat.entity.Home;
import com.steris.wechat.entity.HomeExample;
import com.steris.wechat.service.HomeService;
import com.steris.wechat.util.Constants;
import com.steris.wechat.util.UtilFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeMapper homeMapper;
    @Autowired
    private FileDAO fileDAO;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    @Transactional
    public int add(NewsReq req) {
        try {
            int max = fileDAO.max();
            Home home = new Home();
            home.setName(req.getNewsTitle());
            home.setNewsId(req.getId());
            home.setUrl(Constants.FILE_IMG_PATH + req.getNewsImg());
            home.setSeq(max+1);
            return homeMapper.insertSelective(home);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Add Home error. ErrorInfo={}", e);
            return Constants.INSERT_ERROR;
        }
    }

    @Override
    public List<Home> query(NewsReq req) {
        try {
            HomeExample example = new HomeExample();
            example.setOrderByClause("seq ASC");
            return homeMapper.selectByExample(example);
        }catch (Exception e){
            logger.error("Query Home error. ErrorInfo={}", e);
            return null;
        }
    }

    @Override
    @Transactional
    public int modify(NewsReq req) {
        try {
            Home home = new Home();
            home.setId(req.getId());
            if (req.getNewsImg()!=null){
                Home homes = homeMapper.selectByPrimaryKey(req.getId());
                Boolean flag = UtilFile.delFile(Constants.F_REMOVE + homes.getUrl());
                if (!flag){
                    logger.error("Home modify error.");
                    return Constants.UPDATE_ERROR;
                }
                home.setUrl(Constants.FILE_IMG_PATH + req.getNewsImg());
            }
            if (req.getHeadId()!=null){
                home.setNewsId(req.getHeadId());
            }
            if (req.getNewsTitle()!=null){
                home.setName(req.getNewsTitle());
            }
            if(req.getOldSeq()>req.getSeq()){
                req.setNum(1);
                for (Home h : fileDAO.getId(req)){
                    h.setSeq(h.getSeq()+1);
                    homeMapper.updateByPrimaryKeySelective(h);
                }
            }
            if(req.getOldSeq()<req.getSeq()){
                req.setNum(2);
                for (Home h : fileDAO.getId(req)){
                    h.setSeq(h.getSeq()-1);
                    homeMapper.updateByPrimaryKeySelective(h);
                }
            }
            if (req.getSeq()!= null){
                home.setSeq(req.getSeq());
            }
            int result = homeMapper.updateByPrimaryKeySelective(home);
            if (result != Constants.ADD_SUCESS){
                logger.error("Home modify error.");
                return Constants.UPDATE_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Modify Home error. ErrorInfo={}", e);
            return Constants.UPDATE_ERROR;
        }
    }

    @Override
    @Transactional
    public int delete(Integer id) {
        try {
            Home home = homeMapper.selectByPrimaryKey(id);
            Boolean flag = UtilFile.delFile(Constants.F_REMOVE + home.getUrl());
            if (!flag){
                logger.error("Home delete error.");
                return Constants.UPDATE_ERROR;
            }
            NewsReq req = new NewsReq();
            req.setNum(3);
            req.setSeq(home.getSeq());
            for (Home h : fileDAO.getId(req)){
                h.setSeq(h.getSeq()-1);
                homeMapper.updateByPrimaryKeySelective(h);
            }
            return fileDAO.deleteHomeById(id);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Delete Home error. ErrorInfo={}", e);
            return Constants.UPDATE_ERROR;
        }
    }
}
