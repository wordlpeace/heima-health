package com.itheima.health.config;

import com.itheima.health.util.QiniuUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QiniuConfiguration {

    @ConfigurationProperties("qiniu")
    @Bean
    public QiniuUtils qiniuUtils() {
        return new QiniuUtils();
    }

}