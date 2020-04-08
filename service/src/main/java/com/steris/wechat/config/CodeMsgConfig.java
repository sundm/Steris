package com.steris.wechat.config;

import com.steris.wechat.dto.BasicResp;
import com.steris.wechat.util.Constants;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties
@Configuration
@PropertySource(value = "classpath:codemsg.properties")
@Data
public class CodeMsgConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<String, String> code = new HashMap<>();
    private Map<String, String> msg = new HashMap<>();

    public String getCode(String name) {
        // code全部是数字，不需要转码。
        return code.get(name);
    }

    public String getMsg(String name) {
        try {
            // msg采用中文，需要转码。
            return new String(msg.get(name).getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("Message encoding parsing error. ErrorInfo={}", e);
        }
        return "";  // empty message if error.
    }

    @Bean
    public BasicResp success() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("success"));
        resp.setMsg(getMsg("success"));
        return resp;
    }

    @Bean
    public BasicResp ParameterValueError(){
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("parameter-value-error"));
        resp.setCode(getMsg("parameter-value-error"));
        return resp;
    }

    @Bean
    public BasicResp missingField() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("missing-field"));
        resp.setMsg(getMsg("missing-field"));
        return resp;
    }

    @Bean
    public BasicResp wrongPassword() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("wrong-password"));
        resp.setMsg(getMsg("wrong-password"));
        return resp;
    }

    @Bean
    public BasicResp unmodifiedInstruction() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("unmodified-instruction"));
        resp.setMsg(getMsg("unmodified-instruction"));
        return resp;
    }

    @Bean
    public BasicResp noPictures() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("no-pictures"));
        resp.setMsg(getMsg("no-pictures"));
        return resp;
    }

    @Bean
    public BasicResp noPicturesHash() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("success"));
        resp.setMsg(getMsg("success"));
        return resp;
    }

    @Bean
    public BasicResp sterilizeIdNotExist() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("sterilize-id-not-exist"));
        resp.setMsg(getMsg("sterilize-id-not-exist"));
        return resp;
    }

    @Bean
    public BasicResp sterilizePhotoExist() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("sterilize-photo-exist"));
        resp.setMsg(getMsg("sterilize-photo-exist"));
        return resp;
    }

    @Bean
    public BasicResp downloadPicturesSuccess() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("success"));
        resp.setMsg(getMsg("success"));
        return resp;
    }


    @Bean
    public BasicResp invalidParameter() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("invalid-parameter"));
        resp.setMsg(getMsg("invalid-parameter"));
        return resp;
    }

    @Bean
    public BasicResp nameExist() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("name-exists"));
        resp.setMsg(getMsg("name-exists"));
        return resp;
    }

    @Bean
    public BasicResp insertError() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("insert-error"));
        resp.setMsg(getMsg("insert-error"));
        return resp;
    }

    @Bean
    public BasicResp updateError() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("update-error"));
        resp.setMsg(getMsg("update-error"));
        return resp;
    }

    @Bean
    public BasicResp searchError() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("search-error"));
        resp.setMsg(getMsg("search-error"));
        return resp;
    }

    @Bean
    public BasicResp searchNull() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("search-null"));
        resp.setMsg(getMsg("search-null"));
        return resp;
    }

    @Bean
    public BasicResp plateAlreadyInUse() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("plate-in-use"));
        resp.setMsg(getMsg("plate-in-use"));
        return resp;
    }

    @Bean
    public BasicResp loginFailed(){
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("login-failed"));
        resp.setMsg(getMsg("login-failed"));
        return resp;
    }

    @Bean
    public BasicResp loginFail(){
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("login-fail"));
        resp.setMsg(getMsg("login-fail"));
        return resp;
    }

    @Bean
    public BasicResp loginStateFailed(){
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("loginState-failed"));
        resp.setMsg(getMsg("loginState-failed"));
        return resp;
    }

    @Bean
    public BasicResp loginOnlineFailed(){
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("loginOnline-failed"));
        resp.setMsg(getMsg("loginOnline-failed"));
        return resp;
    }

    @Bean
    public BasicResp addCheckError() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("add-check-error"));
        resp.setMsg(getMsg("add-check-error"));
        return resp;
    }

    @Bean
    public BasicResp duplicateAdd() {
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("duplicate-add"));
        resp.setMsg(getMsg("duplicate-add"));
        return resp;
    }

    public BasicResp noSuchRole(){
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("no-such-role"));
        resp.setMsg(getMsg("no-such-role"));
        return resp;
    }

    public BasicResp fileUploadError(){
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("file-upload-failed"));
        resp.setMsg(getCode("file-upload-failed"));
        return resp;
    }


    @Bean
    public BasicResp roleDup(){
        BasicResp resp = new BasicResp();
        resp.setCode(getCode("role-dup"));
        resp.setMsg(getMsg("role-dup"));
        return resp;
    }

    public void setSuccessMsg(BasicResp resp) {
        resp.setMsg(getMsg("success"));
        resp.setCode(getCode("success"));
    }

    public BasicResp getErrorResp(int errorCode) {
        BasicResp resp = null;
        switch (errorCode) {
            case Constants.NAME_EXISTS:
                resp = nameExist();
                break;
            case Constants.INSERT_ERROR:
                resp = insertError();
                break;
            case Constants.UPDATE_ERROR:
                resp = updateError();
                break;
            case Constants.PLATE_ALREADY_IN_USE:
                resp = plateAlreadyInUse();
                break;
            case Constants.ADD_CHECK_ERROR:
                resp = addCheckError();
                break;
            case Constants.REPEAT_ADD:
                resp = duplicateAdd();
                break;
            case Constants.FILE_UPLOAD_ERROR:
                resp = fileUploadError();
                break;
            case Constants.RECALLED_ERROR:
                resp = searchNull();
                break;
            default:
                break;
        }
        return resp;
    }

    public void setExceptionMsg(BasicResp resp){
        resp.setMsg(getMsg("exception"));
        resp.setCode(getCode("exception"));
    }

}
