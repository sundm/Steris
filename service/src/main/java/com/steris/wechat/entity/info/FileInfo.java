package com.steris.wechat.entity.info;

import lombok.Data;

@Data
public class FileInfo {
    private Integer id;
    private Integer typeId;
    private String name;
    private String concent;
    private String url;
    private String typeName;
    private Integer seq;
    private String state;
    private String type;
}
