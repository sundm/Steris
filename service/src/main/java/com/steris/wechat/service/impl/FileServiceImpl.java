package com.steris.wechat.service.impl;

import com.steris.wechat.dao.FileTypeMapper;
import com.steris.wechat.dao.FilesMapper;
import com.steris.wechat.dao.sql.FileDAO;
import com.steris.wechat.dto.request.FileReq;
import com.steris.wechat.entity.FileType;
import com.steris.wechat.entity.FileTypeExample;
import com.steris.wechat.entity.Files;
import com.steris.wechat.entity.info.FileInfo;
import com.steris.wechat.service.FileService;
import com.steris.wechat.util.Constants;
import com.steris.wechat.util.UtilFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDAO fileDAO;
    @Autowired
    private FilesMapper filesMapper;
    @Autowired
    private FileTypeMapper fileTypeMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /*
    * files
    * */
    @Override
    public List<FileInfo> query(FileReq req) {
        try {
            List<FileInfo> fileInfos = fileDAO.search(req);
            if (fileInfos == null){
                logger.error("Search FileInfos Null");
                return null;
            }
            return fileInfos;
        }catch (Exception e){
            logger.error("Search FileInfos Is Error.",e);
            return null;
        }
    }

    @Override
    public Files search(FileReq req) {
        try {
            Files files = filesMapper.selectByPrimaryKey(req.getId());
            return files;
        }catch (Exception e){
            logger.error("Search FileInfos Is Error.",e);
            return null;
        }
    }

    @Override
    @Transactional
    public int add(FileReq req) {
        try {
            Files files = new Files();
            files.setName(req.getName());
            files.setState(req.getState());
            files.setType(req.getTypeId());
            files.setUrl(Constants.FILE_FILE_PATH + req.getUrl());
            files.setConcent(req.getConcent());
            int result = filesMapper.insertSelective(files);
            if (result != Constants.ADD_SUCESS){
                logger.error("FileType Add error.");
                return Constants.INSERT_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("FileType Add error.",e);
            return Constants.INSERT_ERROR;
        }
    }

    @Override
    @Transactional
    public int modify(FileReq req) {
        try {
            Files files = new Files();
            files.setId(req.getId());
            if (req.getState()!=null){
                if (req.getState().equals("2"))
                    files.setState("1");
                else if (req.getState().equals("1"))
                    files.setState("2");
            }
            if (req.getConcent()!=null)
                files.setConcent(req.getConcent());
            if (req.getName()!=null){
                files.setState(req.getState());
                files.setName(req.getName());
            }
            if (req.getTypeId()!=null)
                files.setType(req.getTypeId());
            int result = filesMapper.updateByPrimaryKeySelective(files);
            if (result != Constants.ADD_SUCESS){
                logger.error("Files modify error.");
                return Constants.UPDATE_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Files modify error.",e);
            return Constants.UPDATE_ERROR;
        }
    }

    @Override
    @Transactional
    public int remove(FileReq req) {
        try {
           Files files = filesMapper.selectByPrimaryKey(req.getId());
           Boolean flag = UtilFile.delFile(Constants.F_REMOVE + files.getUrl());
           if (!flag){
               logger.error("Files delete error.");
               return Constants.UPDATE_ERROR;
           }
           int result = fileDAO.deleteFilesById(req);
            if (result != Constants.ADD_SUCESS){
                logger.error("Files delete error.");
                return Constants.UPDATE_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Files delete error.",e);
            return Constants.UPDATE_ERROR;
        }
    }

    /*
    * fileType
    * */
    @Override
    public List<FileType> searchType(FileReq req) {
        try {
            FileTypeExample example = new FileTypeExample();
            FileTypeExample.Criteria criteria = example.createCriteria();
            if (req.getType()!= null && !req.getType().equals("")){
                criteria.andTypeEqualTo(req.getType());
            }
            if (req.getState()!= null){
                criteria.andStateEqualTo(req.getState());
            }
            example.setOrderByClause("id DESC");
            List<FileType> fileTypes = fileTypeMapper.selectByExample(example);
            if (fileTypes == null){
                logger.error("Search FileType Null");
                return null;
            }
            return fileTypes;
        }catch (Exception e){
            logger.error("Search FileType Is Error.",e);
            return null;
        }
    }

    @Override
    @Transactional
    public int addType(FileReq req) {
        try {
            FileType fileType = new FileType();
            fileType.setName(req.getTypeName());
            fileType.setSeq(1);
            fileType.setState(req.getState());
            fileType.setType(req.getType());
            int result = fileTypeMapper.insertSelective(fileType);
            if (result != Constants.ADD_SUCESS){
                logger.error("FileType Add error.");
                return Constants.INSERT_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("FileType Add error.",e);
            return Constants.INSERT_ERROR;
        }
    }

    @Override
    @Transactional
    public int modifyType(FileReq req) {
        try {
            FileType type = new FileType();
            type.setId(req.getId());
            if (req.getState()!=null){
                if (req.getState().equals("2")){
                    type.setState("1");
                } else if (req.getState().equals("1")){
                    type.setState("2");
                }
            }
            if (req.getTypeName()!=null)
                type.setName(req.getTypeName());
            if (req.getType()!=null){
                type.setType(req.getType());
                type.setState(req.getState());
            }
            int result = fileTypeMapper.updateByPrimaryKeySelective(type);
            if (result != Constants.ADD_SUCESS){
                logger.error("FileType modify error.");
                return Constants.INSERT_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("FileType modify error.",e);
            return Constants.UPDATE_ERROR;
        }
    }

    @Override
    @Transactional
    public int removeType(FileReq req) {
        try {
            int result = fileDAO.deleteFileTypeById(req);
            if (result != Constants.ADD_SUCESS){
                logger.error("FilesType removeType error.");
                return Constants.UPDATE_ERROR;
            }
            return result;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("FilesType delete error.",e);
            return Constants.UPDATE_ERROR;
        }
    }


}
