package com.steris.wechat.dao;

import com.steris.wechat.entity.User;
import com.steris.wechat.entity.UserExample;
import java.util.List;

public interface UserMapper {
    int countByExample(UserExample example);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String openId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}