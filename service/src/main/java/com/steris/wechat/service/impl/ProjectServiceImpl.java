package com.steris.wechat.service.impl;

import com.steris.wechat.dao.ProjectMapper;
import com.steris.wechat.dao.UserMapper;
import com.steris.wechat.dao.sql.FileDAO;
import com.steris.wechat.dto.request.ProjectReq;
import com.steris.wechat.entity.Project;
import com.steris.wechat.entity.ProjectExample;
import com.steris.wechat.entity.User;
import com.steris.wechat.service.ProjectService;
import com.steris.wechat.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private FileDAO fileDAO;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectMapper projectMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    @Transactional
    public int add(ProjectReq req) {
        try {
            Project project = new Project();
            project.setuId(req.getUId());
            project.setName(req.getName());
            project.setpName(req.getPName());
            project.setAddress(req.getAddress());
            project.setHosp(req.getHosp());
            project.setDay(req.getDay());
            if (req.getState()!=null)
                project.setState(req.getState());
            User user = userMapper.selectByPrimaryKey(req.getUId());
            project.setRedu(user.getReferrer());
            int result = projectMapper.insertSelective(project);
            if (result != Constants.ADD_SUCESS){
                logger.error("Project Add error.");
                return Constants.INSERT_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Project Add error.",e);
            return Constants.INSERT_ERROR;
        }
    }

    @Override
    public List<Project> search(ProjectReq req) {
        try {
            ProjectExample example = new ProjectExample();
            ProjectExample.Criteria criteria = example.createCriteria();
            if (req.getUId()!=null)
                criteria.andUIdEqualTo(req.getUId());
            if (req.getRedu()!=null)
                criteria.andReduLike("%"+req.getRedu()+"%");
            List<Project> list = projectMapper.selectByExample(example);
            if (list == null){
                logger.error("Search Project Null");
                return null;
            }
            return list;
        }catch (Exception e){
            logger.error("Search Project Is Error.",e);
            return null;
        }
    }

    @Override
    public Project selectByPrimaryKey(ProjectReq req) {
        try {
            Project project = projectMapper.selectByPrimaryKey(req.getId());
            return project;
        }catch (Exception e){
            logger.error("Search ProjectById Is Error.",e);
            return null;
        }
    }

    @Override
    @Transactional
    public int modify(ProjectReq req) {
        try {
            Project project = new Project();
            project.setId(req.getId());
            project.setuId(req.getUId());
            project.setName(req.getName());
            project.setpName(req.getPName());
            project.setAddress(req.getAddress());
            project.setHosp(req.getHosp());
            project.setDay(req.getDay());
            if (req.getState()!=null)
                project.setState(req.getState());
            if (req.getRedu()!=null)
                project.setRedu(req.getRedu());
            int result = projectMapper.updateByPrimaryKey(project);
            if (result != Constants.ADD_SUCESS){
                logger.error("Project Modify error.");
                return Constants.UPDATE_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Project Modify error.",e);
            return Constants.UPDATE_ERROR;
        }
    }

    @Override
    @Transactional
    public int remove(Integer id) {
        try {
            int result = fileDAO.deleteProjectById(id);
            if (result != Constants.ADD_SUCESS){
                logger.error("Project Remove error.");
                return Constants.UPDATE_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Project Remove error.",e);
            return Constants.INSERT_ERROR;
        }
    }
}
