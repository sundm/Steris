package com.steris.wechat.service;

import com.steris.wechat.dto.request.ProductReq;
import com.steris.wechat.entity.Product;
import com.steris.wechat.entity.info.ProductList;

import java.util.List;

public interface ProductService {
    List<ProductList> getProduct(ProductReq req);
    int add(ProductReq req);
    List<Product> search(ProductReq req);
    List<String> searchName(ProductReq req);
    Product query(ProductReq req);
    int modify(ProductReq req);
    int remove(Integer id);
}
