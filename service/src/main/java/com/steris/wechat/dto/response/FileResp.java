package com.steris.wechat.dto.response;

import com.steris.wechat.dto.BasicResp;
import com.steris.wechat.entity.FileType;
import com.steris.wechat.entity.Files;
import com.steris.wechat.entity.info.FileInfo;
import lombok.Data;

import java.util.List;

@Data
public class FileResp extends BasicResp {
    private List<FileInfo> fileInfo;
    private List<FileType> typeInfo;
    private Files files;
}
