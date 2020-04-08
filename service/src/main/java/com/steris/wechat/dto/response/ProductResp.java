package com.steris.wechat.dto.response;

import com.steris.wechat.dto.BasicResp;
import com.steris.wechat.entity.*;
import com.steris.wechat.entity.info.ProductList;
import lombok.Data;

import java.util.List;

@Data
public class ProductResp extends BasicResp {
    private List<Product> products;
    private Product product;
    private List<ProType> proTypes;
    private List<ProductList> detail;
    private List<String> name;
    private String html;


    private List<Tem> temp;
    private Tem tem;
}
