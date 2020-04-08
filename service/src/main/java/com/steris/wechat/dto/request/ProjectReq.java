package com.steris.wechat.dto.request;

import lombok.Data;

@Data
public class ProjectReq {
    private Integer id;
    private String uId;
    private String name;
    private String pName;
    private String address;
    private String hosp;
    private String day;
    private String state;
    private String redu;
}
