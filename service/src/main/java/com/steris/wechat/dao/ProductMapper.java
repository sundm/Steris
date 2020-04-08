package com.steris.wechat.dao;

import com.steris.wechat.entity.Product;
import com.steris.wechat.entity.ProductExample;
import java.util.List;

public interface ProductMapper {
    int countByExample(ProductExample example);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}