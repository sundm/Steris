package com.steris.wechat.util;

import org.springframework.scheduling.annotation.Scheduled;

/*
* 定时刷新AccessToken
* */
public class updateAt {
    @Scheduled(fixedRate=1000*60*58*2)//服务器启动时执行一次，之后每隔一个小时58分执行一次。
    protected void updateWx() throws Exception {
        /*String token=TokenUtils.getToken();
        String jsTicket=WxJsApiUtils.getJsApiTicket(token);
        WxToken wxToken=new WxToken();
        wxToken.setId(1);
        wxToken.setToken(token);
        wxToken.setJsapiTicket(jsTicket);
        tokenMapper.updateByPrimaryKeySelective(wxToken);*/
    }
}

