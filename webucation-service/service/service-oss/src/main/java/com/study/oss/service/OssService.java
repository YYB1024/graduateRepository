package com.study.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: webucation-service
 * @ClassName: OssService
 * @author：yangyanbin
 * @Desc：TODO
 * @date 2021/4/20 22:51
 * @Version: 1.0
 */
public interface OssService {
    /**上传头像到接口中*/
    String uploadFileAvater(MultipartFile file);
}
