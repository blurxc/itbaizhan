package com.bjsxt.backendItem.service;

import com.bjsxt.utils.Result;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    Result fileUpload(MultipartFile file);
}
