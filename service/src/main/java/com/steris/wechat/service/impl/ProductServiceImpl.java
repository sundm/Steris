package com.steris.wechat.service.impl;

import com.steris.wechat.dao.ProDetailMapper;
import com.steris.wechat.dao.ProTypeMapper;
import com.steris.wechat.dao.ProductMapper;
import com.steris.wechat.dao.sql.FileDAO;
import com.steris.wechat.dto.request.ProductReq;
import com.steris.wechat.entity.*;
import com.steris.wechat.entity.info.ProductList;
import com.steris.wechat.service.ProductService;
import com.steris.wechat.util.Constants;
import com.steris.wechat.util.JsonUtil;
import com.steris.wechat.util.UtilFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private FileDAO fileDAO;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProTypeMapper proTypeMapper;
    @Autowired
    private ProDetailMapper proDetailMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<ProductList> getProduct(ProductReq req) {
        try {
            List<ProductList> product = new ArrayList<>();
            ProTypeExample example = new ProTypeExample();
            example.createCriteria().andStateEqualTo(1);

            List<ProType> typeList = proTypeMapper.selectByExample(example);
            for (ProType pro:typeList){
                ProductList proList = new ProductList();
                proList.setId("s" + pro.getId().toString());
                proList.setName(pro.getName());

                ProductExample productExample = new ProductExample();
                productExample.createCriteria().andStateEqualTo(1).andTypeIdEqualTo(pro.getId());
                proList.setDetail(productMapper.selectByExample(productExample));

                product.add(proList);
            }
            return product;
        }catch (Exception e){
            logger.error("getProduct Is Error.",e);
            return null;
        }
    }

    @Override
    @Transactional
    public int add(ProductReq req) {
        try {
            String top = "<html lang='en'><head><meta charset='utf-8'></head><body>";
            String foot = "</body></html>";
            String filePath = UtilFile.saveHtml((top + req.getHtml() + foot),Constants.HTML_UPLOAD);
            if (filePath.equals("error")){
                logger.error("Product Add error.");
                return Constants.INSERT_ERROR;
            }
            Product record = new Product();
            record.setState(2);
            record.setName(req.getName());
            record.setImg(Constants.FILE_IMG_PATH + req.getImgUrl());
            record.setIntro(filePath);
            record.setTypeId(req.getProId());
            record.setPrice(req.getPrice());
            int result = productMapper.insertSelective(record);
            if (result != Constants.ADD_SUCESS){
                logger.error("Product Add error.");
                return Constants.INSERT_ERROR;
            }
            return Constants.ADD_SUCESS;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Product Add error.",e);
            return Constants.INSERT_ERROR;
        }
    }

    @Override
    public List<Product> search(ProductReq req) {
        try {
            ProductExample example = new ProductExample();
            if (req.getState() !=null){
                example.createCriteria().andStateEqualTo(req.getState());
            }
            example.setOrderByClause("id DESC");
            List<Product> products = productMapper.selectByExample(example);
            return products;
        }catch (Exception e){
            logger.error("SearchProduct Is Error.",e);
            return null;
        }
    }

    @Override
    public List<String> searchName(ProductReq req) {
        try {
            ProductExample example = new ProductExample();
            if (req.getState() !=null){
                example.createCriteria().andStateEqualTo(req.getState());
            }
            example.setOrderByClause("id DESC");
            List<Product> products = productMapper.selectByExample(example);
            List<String> proName = new ArrayList<>();
            for (Product product: products){
                proName.add(product.getName());
            }
            return proName;
        }catch (Exception e){
            logger.error("SearchProduct Is Error.",e);
            return null;
        }
    }

    @Override
    public Product query(ProductReq req) {
        try {
            Product products = productMapper.selectByPrimaryKey(req.getId());
            if (req.getProState()==Constants.PRO_ABLE){
            }else if (req.getProState()==Constants.PRO_DISABLE){
            }
            String path =  Constants.HTML_UPLOAD + products.getIntro();
            String html = JsonUtil.readJsonFile(path);
            products.setAfterSale(html);
            return products;
        }catch (Exception e){
            logger.error("QueryProduct Is Error.",e);
            return null;
        }
    }

    @Override
    @Transactional
    public int modify(ProductReq req) {
        try {
            String top = "<html lang='en'><head><meta charset='utf-8'></head><body>";
            String foot = "</body></html>";
            Product product = new Product();
            product.setId(req.getId());
            Product pro = productMapper.selectByPrimaryKey(req.getId());

            if (req.getName()!=null){
                product.setName(req.getName());
            }
            if (req.getProId()!=null){
                product.setTypeId(req.getProId());
            }
            if (req.getImgUrl()!=null){
                Product project = productMapper.selectByPrimaryKey(req.getId());
                Boolean flag = UtilFile.delFile(Constants.F_REMOVE + project.getImg());
                if (!flag){
                    logger.error("Product modify error.");
                    return Constants.UPDATE_ERROR;
                }
                product.setImg(Constants.FILE_IMG_PATH + req.getImgUrl());
            }
            if (req.getPrice()!=null){
                product.setPrice(req.getPrice());
            }
            if (req.getState()!=null){
                if (req.getState()==Constants.PRO_ABLE){
                    product.setState(Constants.PRO_DISABLE);
                }else if (req.getState()==Constants.PRO_DISABLE){
                    product.setState(Constants.PRO_ABLE);
                }
            }
            if (req.getHtml()!=null && !req.getHtml().equals("")){
                UtilFile.deleteHtml(Constants.HTML_UPLOAD,pro.getAfterSale());
                String filePath =UtilFile.saveHtml((top + req.getHtml() + foot),Constants.HTML_UPLOAD);
                if (filePath.equals("error")){
                    logger.error("NewsHead Update error.");
                    return Constants.UPDATE_ERROR;
                }
                product.setIntro(filePath);
            }

            int result = productMapper.updateByPrimaryKeySelective(product);
            if (result == 0){
                logger.error("Product modify error.");
                return Constants.UPDATE_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Product modify error.",e);
            return Constants.UPDATE_ERROR;
        }
    }

    @Override
    @Transactional
    public int remove(Integer id) {
        try {
            Product product = productMapper.selectByPrimaryKey(id);
            Boolean html = UtilFile.delFile(Constants.HTML_UPLOAD + product.getIntro());
            Boolean img = UtilFile.delFile(Constants.F_REMOVE + product.getImg());
            if (!html || !img){
                logger.error("Product Remove error.");
                return Constants.UPDATE_ERROR;
            }
            int result = fileDAO.deleteProductById(id);
            if (result != Constants.ADD_SUCESS){
                logger.error("Product delete error.");
                return Constants.UPDATE_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Product delete error.",e);
            return Constants.UPDATE_ERROR;
        }
    }
}
