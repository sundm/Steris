package com.steris.wechat.dto.response;

import com.steris.wechat.dto.BasicResp;
import com.steris.wechat.entity.Home;
import com.steris.wechat.entity.News;
import com.steris.wechat.entity.NewsHead;
import lombok.Data;

import java.util.List;

@Data
public class NewsResp extends BasicResp {
    private List<News> newsInfo;
    private List<NewsHead> headInfo;
    private List<Home> homes;
    private NewsHead head;
    private String html;
}
