package com.steris.wechat.service.impl;

import com.steris.wechat.dao.ProTypeMapper;
import com.steris.wechat.dto.request.ProductReq;
import com.steris.wechat.entity.ProType;
import com.steris.wechat.entity.ProTypeExample;
import com.steris.wechat.service.ProTypeService;
import com.steris.wechat.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
@Service
public class ProTypeServiceImpl implements ProTypeService {
    @Autowired
    private ProTypeMapper proTypeMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    @Transactional
    public int add(ProductReq req) {
        try {
            ProType proType = new ProType();
            proType.setName(req.getName());
            proType.setState(req.getState());
            proType.setSeq(0);
            int result = proTypeMapper.insertSelective(proType);
            if (result == 0){
                logger.error("ProType Add error.");
                return Constants.INSERT_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("ProType Add error.",e);
            return Constants.INSERT_ERROR;
        }
    }

    @Override
    public List<ProType> search(ProductReq req) {
        try {
            ProTypeExample example = new ProTypeExample();
            if (req.getState()!=null){
                example.createCriteria().andStateEqualTo(req.getState());
            }
            List<ProType> typeList = proTypeMapper.selectByExample(example);
            return typeList;
        }catch (Exception e){
            logger.error("SearchProType Is Error.",e);
            return null;
        }
    }

    @Override
    @Transactional
    public int modify(ProductReq req) {
        try {
            ProType proType = new ProType();
            proType.setId(req.getProId());
            if (req.getProState()==Constants.PRO_ABLE){
                proType.setState(Constants.PRO_DISABLE);
            }else if (req.getProState()==Constants.PRO_DISABLE){
                proType.setState(Constants.PRO_ABLE);
            }
            int result = proTypeMapper.updateByPrimaryKeySelective(proType);
            if (result == 0){
                logger.error("ProType modify error.");
                return Constants.UPDATE_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("ProType modify error.",e);
            return Constants.UPDATE_ERROR;
        }
    }
}
