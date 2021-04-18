package com.study.eduService.controller;

import com.study.utils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: webucation-service
 * @ClassName: EduLoginController
 * @author：yangyanbin
 * @Desc：模拟登录
 * @date 2021/4/18 16:52
 * @Version: 1.0
 */
@Api(description = "登录管理")
@RestController
@RequestMapping("/eduService/user")
@CrossOrigin
public class EduLoginController {
    /**
     *@Desc:登录
     *@param
     *@return:R
     */
    @PostMapping("login")
    public R login(){
        return  R.ok().data("token","admin");
    }

    /**
     *@Desc:获得用户信息
     *@param
     *@return:R
     */
    @GetMapping("info")
    public R info(){
        return  R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
