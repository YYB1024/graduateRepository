package com.study.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.study.oss.service.OssService;
import com.study.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @ProjectName: webucation-service
 * @ClassName: OssServiceImpl
 * @author：yangyanbin
 * @Desc：TODO
 * @date 2021/4/20 22:52
 * @Version: 1.0
 */
@SuppressWarnings("all")
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvater(MultipartFile file) {
        //1、通过工具类获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;


        try {
            //2、判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
    /*  if (!ossClient.doesBucketExist(bucketName)) {
                //创建bucket
                ossClient.createBucket(bucketName);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }*/

            //3、获取上传文件流
            InputStream inputStream = file.getInputStream();


            //4、获取文件名称
            String fileName = file.getOriginalFilename();
            //4、1：在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid + fileName;
            //4、2把文件进行日期分类
            //获取当前的日期
            //构建日期路径：avatar/2019/02/26/文件名
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //4、3拼接
            fileName = datePath + "/" + fileName;

            //5、文件上传至阿里云
            //参数1：Bucket 名字
            //参数2：上传到OSS文件路径和文件名称 aa/bb/1.jpg
            //参数3：上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 6、关闭OSSClient。
            ossClient.shutdown();

            //获取url地址（手动拼接）
            String uploadUrl = "http://" + bucketName + "." + endPoint + "/" + fileName;
            return uploadUrl;
        } catch (Exception e) {
            //throw new GuliException(ResultCodeEnum.FILE_UPLOAD_ERROR);
            e.printStackTrace();
            return null;
        }

    }
}
