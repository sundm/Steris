package com.steris.wechat.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "parameter")
@Data
public class Parameter {

    /**
     * 小程序appid
     */
    private String APPID;
    /**
     * 小程序appSecrect
     */
    private String APP_SECRECT;
}
