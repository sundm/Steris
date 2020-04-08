package com.steris.wechat.service;

import com.steris.wechat.dto.request.NewsReq;
import com.steris.wechat.entity.Home;
import com.steris.wechat.entity.HomeExample;

import java.util.List;

public interface HomeService {
    int add(NewsReq req);
    List<Home> query(NewsReq req);
    int modify(NewsReq req);
    int delete(Integer id);
}
