package com.steris.wechat.dto.response;

import com.steris.wechat.dto.BasicResp;
import com.steris.wechat.entity.Mail;
import com.steris.wechat.entity.User;
import lombok.Data;

import java.util.List;
@Data
public class UserInfoResp extends BasicResp {
    private List<User> userList;
    private List<Mail> mail;
    private User userInfo;
    private String state;
    private String price;
}
