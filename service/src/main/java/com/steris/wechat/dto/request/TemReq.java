package com.steris.wechat.dto.request;

import lombok.Data;

@Data
public class TemReq {
    private Integer id;
    private String name;
    private String url;
    private String state;
}
