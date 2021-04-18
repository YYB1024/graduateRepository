package com.study.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: webucation-service
 * @ClassName: GuliException
 * @author：yangyanbin
 * @Desc：创建自定义异常类
 * @date 2021/4/18 14:26
 * @Version: 1.0
 * @AllArgsConstructor:生成有参数的构造方法
 * @NoArgsConstructor:生成无参的构造方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException  extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "异常信息")
    private String msg;
}
