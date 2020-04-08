package com.steris.wechat.dto.response;

import com.steris.wechat.dto.BasicResp;
import com.steris.wechat.entity.Admin;
import lombok.Data;

@Data
public class AdminResp extends BasicResp {
    private Admin admin;
}
