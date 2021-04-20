package com.study.oss.controller;


import com.study.oss.service.OssService;
import com.study.oss.utils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: webucation-service
 * @ClassName: OssController
 * @author：yangyanbin
 * @Desc：OSS控制类
 * @date 2021/4/20 22:52
 * @Version: 1.0
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;
    /**
     *@Desc:1、上传头像的方法
     *@param
     *@return:com.study.oss.utils.R
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R uploadOssFile(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file){
        //获取上传文件MultipartFile
        //返回上传路径
        String url = ossService.uploadFileAvater(file);
        return R.ok().message("文件上传成功").data("url",url);
    }

}
