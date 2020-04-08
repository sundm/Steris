package com.steris.wechat.entity;

import java.util.Date;

public class NewsHead {
    private Integer id;

    private String nTitle;

    private String nContent;

    private String nImg;

    private Integer nSeq;

    private Date createTime;

    private String state;

    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getnTitle() {
        return nTitle;
    }

    public void setnTitle(String nTitle) {
        this.nTitle = nTitle == null ? null : nTitle.trim();
    }

    public String getnContent() {
        return nContent;
    }

    public void setnContent(String nContent) {
        this.nContent = nContent == null ? null : nContent.trim();
    }

    public String getnImg() {
        return nImg;
    }

    public void setnImg(String nImg) {
        this.nImg = nImg == null ? null : nImg.trim();
    }

    public Integer getnSeq() {
        return nSeq;
    }

    public void setnSeq(Integer nSeq) {
        this.nSeq = nSeq;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}