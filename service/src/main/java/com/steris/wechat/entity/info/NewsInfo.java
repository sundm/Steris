package com.steris.wechat.entity.info;

import lombok.Data;

@Data
public class NewsInfo {
    //连接部分
    private Integer id;
    private Integer hId;
    private Integer cId;
    private Integer iId;
    private Integer num;
    private String type;
    //简要信息
    private String nTitle;
    private String nContent;
    private String nImg;
    private Integer nSeq;
    private String state;
    //详情文字
    private String con;
    //详情图片
    private String url;
}
