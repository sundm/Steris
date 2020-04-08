package com.steris.wechat.service;

import com.steris.wechat.dto.request.NewsReq;
import com.steris.wechat.dto.response.NewsResp;
import com.steris.wechat.entity.NewsHead;

import java.util.List;

public interface NewsService {
    int add(NewsReq req);
    int modify(NewsReq req);
    List<NewsHead> searchHead(NewsReq req);
    int remove(NewsReq req);
    NewsResp searchNews(NewsReq req);
}
