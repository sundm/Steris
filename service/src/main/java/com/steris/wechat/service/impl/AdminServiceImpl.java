package com.steris.wechat.service.impl;

import com.steris.wechat.dao.AdminMapper;
import com.steris.wechat.dto.request.AdminReq;
import com.steris.wechat.entity.Admin;
import com.steris.wechat.entity.AdminExample;
import com.steris.wechat.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public List<Admin> selectByExample(AdminReq req) {
        try {
            AdminExample example = new AdminExample();
            example.createCriteria().andAccountEqualTo(req.getAccount());
            example.createCriteria().andPasswordEqualTo(req.getPassword());
            List<Admin> adminList = adminMapper.selectByExample(example);
            return adminList;
        }catch (Exception e){
            logger.error("SearchProType Is Error.",e);
            return null;
        }
    }

    @Override
    public int updateByPrimaryKeySelective(AdminReq req) {
        return 0;
    }
}
