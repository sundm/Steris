package com.steris.wechat.service.impl;

import com.steris.wechat.dao.MailMapper;
import com.steris.wechat.dao.UserMapper;
import com.steris.wechat.dao.sql.FileDAO;
import com.steris.wechat.dto.request.UserInfoReq;
import com.steris.wechat.entity.Mail;
import com.steris.wechat.entity.MailExample;
import com.steris.wechat.entity.User;
import com.steris.wechat.entity.UserExample;
import com.steris.wechat.service.UserService;
import com.steris.wechat.util.Constants;
import com.steris.wechat.util.EmojiFilter;
import com.vdurmont.emoji.EmojiParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailMapper mailMapper;
    @Autowired
    private FileDAO fileDAO;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public User selectByPrimaryKey(UserInfoReq req) {
        try {
            User user = userMapper.selectByPrimaryKey(req.getOpenId());
            if (user==null){
                logger.error("Search User Null.");
                return null;
            }
            return user;
        }catch (Exception e){
            logger.error("Search user error. ErrorInfo={}", e);
            return null;
        }
    }

    @Override
    @Transactional
    public int addUser(UserInfoReq req) {
        try {
            User user = req.getUserInfo();
            String name = EmojiParser.removeAllEmojis(req.getUserInfo().getNickName());
            if (name==null){
                user.setNickName("");
            }else {
                user.setNickName(name);
            }
            user.setPhone(req.getPhone());
            user.setOpenId(req.getOpenId());
            user.setHosp(req.getHosp());
            user.setJob(req.getJob());
            user.setrId(1);
            user.setState(Constants.USER_STATE_S);
            user.setName(req.getReferrer());
            user.setAddtime(new Date());
            return userMapper.insertSelective(user);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Add UserInfo error. ErrorInfo={}", e);
            return Constants.INSERT_ERROR;
        }
    }

    @Override
    @Transactional
    public int modifyUser(UserInfoReq req) {
        try {
            User user = new User();
            user.setOpenId(req.getOpenId());
            if (req.getState()!=null){
                if (req.getState().equals(Constants.USER_STATE_S)){
                    user.setState(Constants.USER_STATE_SH);
                }else if (req.getState().equals(Constants.USER_STATE_SH)){
                    user.setState(Constants.USER_STATE_S);
                }
            }
            if (req.getPrice()!=null){
                user.setPrice(req.getPrice());
            }
            if (req.getJob()!=null){
                user.setJob(req.getJob());
            }
            if (req.getRId()!=null){
                user.setrId(req.getRId());
            }
            if (req.getName()!=null){
                user.setReferrer(req.getName());
            }
            int result = userMapper.updateByPrimaryKeySelective(user);
            if (result == 0){
                logger.error("User modify error.");
                return Constants.UPDATE_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("User modify error.",e);
            return Constants.UPDATE_ERROR;
        }
    }

    @Override
    public List<User> auth(UserInfoReq req) {
        try {
            UserExample example = new UserExample();
            if (req.getState()!=null){
                example.setOrderByClause("referrer DESC");
            }else {
                example.setOrderByClause("addtime DESC");
            }
            List<User> userList = userMapper.selectByExample(example);
            if (userList == null) {
                logger.error("Auth error. ");
                return null;
            }
            return userList;
        }catch (Exception e){
            logger.error("Auth error. ErrorInfo={}", e);
            return null;
        }
    }

    @Override
    @Transactional
    public int remove(String id) {
        try {
            int result = fileDAO.deleteUserById(id);
            if (result == 0){
                logger.error("User remove error.");
                return Constants.UPDATE_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("User remove error.",e);
            return 0;
        }
    }

    /*
    * Mail 部分
    * */
    @Override
    public List<Mail> getMail(UserInfoReq req) {
        try {
            MailExample example = new MailExample();
            List<Mail> mail = mailMapper.selectByExample(example);
            return mail;
        }catch (Exception e){
            logger.error("Search Mail error. ErrorInfo={}", e);
            return null;
        }
    }

    @Override
    @Transactional
    public int add(UserInfoReq req) {
        try {
            Mail mail = new Mail();
            mail.setName(req.getMailName());
            return mailMapper.insertSelective(mail);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Add UserInfo error. ErrorInfo={}", e);
            return Constants.INSERT_ERROR;
        }
    }

    @Override
    @Transactional
    public int delete(Integer id) {
        try {
           int result = fileDAO.deleteMailById(id);
            if (result == 0){
                logger.error("Mail delete error.");
                return Constants.UPDATE_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Mail delete error.",e);
            return 0;
        }
    }
}
