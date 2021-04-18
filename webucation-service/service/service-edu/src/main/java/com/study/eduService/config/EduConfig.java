package com.study.eduService.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: online_education
 * @ClassName: EduConfig
 * @author：yangyanbin
 * @Desc：3、创建配置类，配置mapper扫描
 * @date 2021/4/15 23:03
 * @Version: 1.0
 */
@Configuration
@MapperScan("com.study.eduService.mapper")
public class EduConfig {
    /**
     * 2、1逻辑删除插件
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
