package com.steris.wechat.dto.request;

import com.steris.wechat.dto.BasicReq;
import com.steris.wechat.entity.NewsHead;
import com.steris.wechat.entity.info.NewAdd;
import lombok.Data;

import java.util.List;

@Data
public class NewsReq extends BasicReq {
    private Integer id;
    private Integer headId;
    private Integer cId;
    private Integer iId;
    private Integer num;
    private String type;
    private List<NewAdd> news;
    private String newsTitle;
    private String newsText;
    private String newsImg;
    private String newsImgName;
    private String state;


    private String html;
    private NewsHead head;

    private Integer seq;
    private Integer oldSeq;
}
