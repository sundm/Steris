package com.steris.wechat.service;

import com.steris.wechat.dto.request.FileReq;
import com.steris.wechat.entity.FileType;
import com.steris.wechat.entity.Files;
import com.steris.wechat.entity.info.FileInfo;

import java.util.List;

public interface FileService {
    List<FileInfo> query(FileReq req);
    Files search(FileReq req);
    int add(FileReq req);
    int modify(FileReq req);
    int remove(FileReq req);

    List<FileType> searchType(FileReq req);
    int addType(FileReq req);
    int modifyType(FileReq req);
    int removeType(FileReq req);
}
