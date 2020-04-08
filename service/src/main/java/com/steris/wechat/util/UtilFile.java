package com.steris.wechat.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

public class UtilFile {
    public static String upload(MultipartFile file,String filePath) {
        String fileName = file.getOriginalFilename();
        if (fileName.indexOf("\\") != -1) {
            fileName = fileName.substring(fileName.lastIndexOf("\\"));
        }
        java.io.File f = new java.io.File(filePath);
        if (!f.exists()) {
            f.mkdirs();// 不存在路径则进行创建
        }
        FileOutputStream out = null;
        try {
            // 重新自定义文件的名称
            filePath = filePath + fileName;
            out = new FileOutputStream(filePath);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            return "error";
        }
        return filePath;
    }

    public static String saveHtml(String html,String filePath) {
        java.io.File f = new java.io.File(filePath);
        if (!f.exists()) {
            f.mkdirs();// 不存在路径则进行创建
        }
        FileOutputStream out = null;
        try {
            String name = RandomUtils.toFixedLengthStringByUUID(10) + ".html";
            // 重新自定义文件的名称
            filePath = filePath + name;
            out = new FileOutputStream(filePath);
            out.write(html.getBytes());
            out.flush();
            out.close();
            return name;
        } catch (Exception e) {
            return "error";
        }
    }
    public static void deleteHtml(String path,String filename) {
        File file=new File(path+filename);
        if(file.exists()&&file.isFile())
            file.delete();
    }
    public static boolean delFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        flag = file.delete();
        return flag;
    }
    /**
     * 写出json文件
     */
    public static void writeJsonFile(String newJsonString, String path){
        try {
            FileWriter fw = new FileWriter(path);
            PrintWriter out = new PrintWriter(fw);
            out.write(newJsonString);
            out.println();
            fw.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
