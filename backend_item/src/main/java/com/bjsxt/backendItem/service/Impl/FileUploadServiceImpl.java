package com.bjsxt.backendItem.service.Impl;


import com.bjsxt.backendItem.service.FileUploadService;
import com.bjsxt.utils.FtpUtil;
import com.bjsxt.utils.IDUtils;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileUploadServiceImpl implements FileUploadService{

    @Value("${FTP_HOST}")
    private String FTP_HOST;

    @Value("${FTP_PORT}")
    private int FTP_PORT;

    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;

    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;

    @Value("${FTP_BASEPATH}")
    private String FTP_BASEPATH;

    @Override
    public Result fileUpload(MultipartFile file) {
        try {
            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("/yyyy/MM/dd/");
            String path = simpleDateFormat.format(new Date());
            String newFileName = IDUtils.genImageName() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            FtpUtil.uploadFile(FTP_HOST,FTP_PORT,FTP_USERNAME,FTP_PASSWORD,FTP_BASEPATH,path,newFileName,file.getInputStream());
            String imageURL="http://"+this.FTP_HOST+path+newFileName;
            return Result.ok(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
