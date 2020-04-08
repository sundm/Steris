package com.steris.wechat.dao;

import com.steris.wechat.entity.Admin;
import com.steris.wechat.entity.AdminExample;
import java.util.List;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}