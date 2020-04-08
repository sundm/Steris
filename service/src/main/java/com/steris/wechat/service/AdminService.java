package com.steris.wechat.service;

import com.steris.wechat.dto.request.AdminReq;
import com.steris.wechat.entity.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> selectByExample(AdminReq req);
    int updateByPrimaryKeySelective(AdminReq req);
}
