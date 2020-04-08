package com.steris.wechat.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasicResp implements Serializable{
    @JSONField(serialize = false)
    private static final long serialVersionUID = -8908378741991782220L;
    private String code;
    private String msg;
}
