package com.steris.wechat.dto.request;

import com.steris.wechat.dto.BasicReq;
import lombok.Data;

@Data
public class AdminReq extends BasicReq {
    private String account;
    private String password;
}
