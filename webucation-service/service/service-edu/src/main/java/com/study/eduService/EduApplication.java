package com.study.eduService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @ProjectName: online_education
 * @ClassName: EduApplication
 * @author：yangyanbin
 * @Desc：Spring boot 启动类
 * @date 2021/4/15 22:57
 * @Version: 1.0
 *
 * @ComponentScan:组件扫描设置要扫描的包(eg：扫描配置的swagger)
 */
@ComponentScan(basePackages = {"com.study"})
@SpringBootApplication
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);

    }
}
