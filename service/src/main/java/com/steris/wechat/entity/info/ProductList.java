package com.steris.wechat.entity.info;

import com.steris.wechat.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductList {
    private String id;
    private String name;
    private List<Product> detail;
}
