package com.bjsxt.backendItem.controller;


import com.bjsxt.backendItem.service.FileUploadService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping("/upload")
    public Result fileUpload (MultipartFile file){

        try {
            return fileUploadService.fileUpload(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }
}
