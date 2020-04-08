package com.steris.wechat.service;

import com.steris.wechat.dto.request.ProductReq;
import com.steris.wechat.entity.ProType;

import java.util.List;

public interface ProTypeService {
    int add(ProductReq req);
    List<ProType> search(ProductReq req);
    int modify(ProductReq req);
}
