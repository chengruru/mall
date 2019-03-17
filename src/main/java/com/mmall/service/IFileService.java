package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by geely
 */
public interface IFileService {

    /**
     *
     * @param file
     * @param path
     * @return 返回上传的文件名
     */
    String upload(MultipartFile file, String path);
}
