package com.steris.wechat.controller;

import com.steris.wechat.config.CodeMsgConfig;
import com.steris.wechat.dto.BasicResp;
import com.steris.wechat.dto.request.NewsReq;
import com.steris.wechat.dto.request.ProductReq;
import com.steris.wechat.dto.response.NewsResp;
import com.steris.wechat.dto.response.ProductResp;
import com.steris.wechat.entity.ProType;
import com.steris.wechat.entity.Product;
import com.steris.wechat.entity.info.ProductList;
import com.steris.wechat.service.ProTypeService;
import com.steris.wechat.service.ProductService;
import com.steris.wechat.util.Constants;
import com.steris.wechat.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pro")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProTypeService proTypeService;
    @Autowired
    private CodeMsgConfig codeMsgConfig;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @PostMapping("getProductList")
    public BasicResp getProductList(@RequestBody ProductReq req){
        List<ProductList> products = productService.getProduct(req);
        if (products==null){
            log.error("Get Product SearchNull.");
            return codeMsgConfig.searchNull();
        }
        ProductResp resp = new ProductResp();
        resp.setDetail(products);
        log.info("Get ProductList Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }

    @PostMapping("add")
    public BasicResp add(@RequestBody ProductReq req){
        int result = productService.add(req);
        if (result == Constants.INSERT_ERROR)
            return codeMsgConfig.insertError();
        log.info("Add Product Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("getProduct")
    public BasicResp getProduct(@RequestBody ProductReq req){
        List<Product> products = productService.search(req);
        List<ProType> proTypes = proTypeService.search(req);
        List<String> proName = productService.searchName(req);
        if (products==null || proTypes==null){
            log.error("Get Product SearchNull.");
            return codeMsgConfig.searchNull();
        }
        ProductResp resp = new ProductResp();
        resp.setProducts(products);
        resp.setProTypes(proTypes);
        resp.setName(proName);
        log.info("Search Product Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }
    @PostMapping("getPro")
    public BasicResp getPro(@RequestBody ProductReq req){
        if (req.getId() == null){
            log.warn("Missing getPro Field.");
            return codeMsgConfig.missingField();
        }
        Product product = productService.query(req);
        if (product==null){
            log.error("Get getPro SearchNull.");
            return codeMsgConfig.searchNull();
        }
        String path =  Constants.HTML_UPLOAD + product.getIntro();
        String html = JsonUtil.readJsonFile(path);
        ProductResp resp = new ProductResp();
        resp.setHtml(html);
        resp.setProduct(product);
        log.info("Search getPro Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }
    @PostMapping("modifyPro")
    public BasicResp modifyPro(@RequestBody ProductReq req){
        if (req.getId() == null ){
            log.warn("Missing Modify ProType Field.");
            return codeMsgConfig.missingField();
        }
        int result = productService.modify(req);
        if (result == Constants.UPDATE_ERROR)
            return codeMsgConfig.updateError();
        log.info("Modify ProType Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("remove")
    public BasicResp remove(@RequestBody ProductReq req){
        if (req.getId()==null || req.getId().equals("")){
            log.warn("Missing Field.");
            return codeMsgConfig.missingField();
        }
        int result = productService.remove(req.getId());
        if (result == Constants.UPDATE_ERROR)
            return codeMsgConfig.updateError();
        log.info("Remove product Success.");
        return codeMsgConfig.success();
    }
    /*
    * ProType
    * */
    @PostMapping("addProType")
    public BasicResp addPro(@RequestBody ProductReq req){
        if (req.getName() == null || req.getName().equals("")){
            log.warn("Missing ProType Field.");
            return codeMsgConfig.missingField();
        }
        int result = proTypeService.add(req);
        if (result == Constants.INSERT_ERROR)
            return codeMsgConfig.insertError();
        log.info("Add ProType Success.");
        return codeMsgConfig.success();
    }
    @PostMapping("getProType")
    public BasicResp getProType(@RequestBody ProductReq req){
        List<ProType> proTypes = proTypeService.search(req);
        if (proTypes==null){
            log.error("Get ProductType SearchNull.");
            return codeMsgConfig.searchNull();
        }
        ProductResp resp = new ProductResp();
        resp.setProTypes(proTypes);
        log.info("Search ProductType Success.");
        codeMsgConfig.setSuccessMsg(resp);
        return resp;
    }
    @PostMapping("modifyProType")
    public BasicResp modifyProType(@RequestBody ProductReq req){
        if (req.getProId() == null || req.getProState() == null){
            log.warn("Missing Modify ProType Field.");
            return codeMsgConfig.missingField();
        }
        int result = proTypeService.modify(req);
        if (result == Constants.UPDATE_ERROR)
            return codeMsgConfig.updateError();
        log.info("Modify ProType Success.");
        return codeMsgConfig.success();
    }
}
