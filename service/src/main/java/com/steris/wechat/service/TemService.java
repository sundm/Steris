package com.steris.wechat.service;

import com.steris.wechat.dto.request.ProductReq;
import com.steris.wechat.dto.request.TemReq;
import com.steris.wechat.dto.response.ProductResp;
import com.steris.wechat.entity.Tem;

import java.util.List;

public interface TemService {
    int add(TemReq req);
    List<Tem> query(TemReq req);
    ProductResp queryById(TemReq req);
    int modify(TemReq req);
    int delete(Integer id);
}
