package com.steris.wechat.dto.request;

import com.steris.wechat.dto.BasicReq;
import lombok.Data;

@Data
public class FileReq extends BasicReq {
    private Integer id;
    private Integer typeId;
    private String name;
    private String url;
    private String concent;

    private String typeName;
    private Integer seq;
    private String state;
    private String type;
    private String types;
}
