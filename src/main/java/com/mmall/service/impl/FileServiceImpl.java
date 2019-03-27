package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by geely
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);


    /**
     * 将文件上传到服务器上
     * @param file  需要上传的文件
     * @param path  文件路径
     * @return  上传文件名
     */
    public String upload(MultipartFile file,String path){

        // 1. 将本地文件上传到tomcat服务器
        // 2. 将tomcat服务器上的文件上传到ftp服务器上
        // 3. 删除tomcat服务器下的上传文件
        // 4. 返回最终上传到服务器的文件名
        // 5. 返回上传文件的原始文件名
        String fileName = file.getOriginalFilename();
        //扩展名
        //abc.jpg  --> jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1); // jpg

        // 防止文件名相同被覆盖
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);

        // 所需要上传的服务器目录文件路径若是不存在，则直接在服务器上创建
        File fileDir = new File(path);
        if(!fileDir.exists()){  // 若不存在就创建,webapp/upload
            fileDir.setWritable(true);  // 设置写权限
            fileDir.mkdirs();   // 创建文件夹
        }
        // 完整的文件名，在tomcat服务器上，上传到ftp服务器之后要删除
        // upload/上传文件
        File targetFile = new File(path,uploadFileName);


        try {
            file.transferTo(targetFile);
            //文件已经上传成功了，位置：/webapp/upload/uploadFileName


            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到ftp服务器上

            // 删除upload文件夹下的文件，否则时间久了，文件会越来越大
            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        }
        //A:abc.jpg
        //B:abc.jpg
        return targetFile.getName();
    }

}
