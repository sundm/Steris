package com.steris.wechat.dto.request;

import lombok.Data;

@Data
public class ProductReq {
    private Integer id;
    private Integer state;
    private Integer proId;
    private Integer proState;
    private String name;
    private String imgUrl;
    private String price;
    private String html;
    private String detailHtml;
}
