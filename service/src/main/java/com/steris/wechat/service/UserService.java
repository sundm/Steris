package com.steris.wechat.service;

import com.steris.wechat.dto.request.UserInfoReq;
import com.steris.wechat.entity.Mail;
import com.steris.wechat.entity.User;

import java.util.List;

public interface UserService {
    User selectByPrimaryKey(UserInfoReq req);
    int addUser(UserInfoReq req);
    int modifyUser(UserInfoReq req);
    List<User> auth(UserInfoReq req);
    int remove(String id);

    List<Mail> getMail(UserInfoReq req);
    int add(UserInfoReq req);
    int delete(Integer id);
}
