package com.study.servicebase.exceptionhandler;


import com.study.oss.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName: webucation-service
 * @ClassName: GlobalExceptionHandler
 * @author：yangyanbin
 * @Desc：创建统一异常处理器
 * @date 2021/4/18 11:55
 * @Version: 1.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**指定出现什么全局异常执行这个方法*/
    @ExceptionHandler(Exception.class)
    /**为了返回数据*/
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理。。");
    }

    /**指定出现什么特定异常执行这个方法*/
    @ExceptionHandler(ArithmeticException.class)
    /**为了返回数据*/
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理。。");
    }
    /**指定自定义异常执行这个方法*/
    @ExceptionHandler(GuliException.class)
    /**为了返回数据*/
    @ResponseBody
    public R error(GuliException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
