package com.steris.wechat.dto.request;

import com.steris.wechat.dto.BasicReq;
import com.steris.wechat.entity.User;
import lombok.Data;

@Data
public class UserInfoReq extends BasicReq {
    private String openId;
    private Integer rId;
    private String state;
    private String phone;
    private String job;
    private String hosp;
    private String referrer;
    private String price;
    private String name;

    private String mailName;

    private User userInfo;
}
