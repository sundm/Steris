package com.steris.wechat.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicReq implements Serializable{
    @JSONField(serialize = false)
    private static final long serialVersionUID = -8368185237257169038L;

    private String version;
    private String token;
}
