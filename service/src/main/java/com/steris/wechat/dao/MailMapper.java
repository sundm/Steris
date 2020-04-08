package com.steris.wechat.dao;

import com.steris.wechat.entity.Mail;
import com.steris.wechat.entity.MailExample;
import java.util.List;

public interface MailMapper {
    int countByExample(MailExample example);

    int insert(Mail record);

    int insertSelective(Mail record);

    List<Mail> selectByExample(MailExample example);

    Mail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mail record);

    int updateByPrimaryKey(Mail record);
}