package com.itheima.health;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：zhang
 * @date ：Created in 2020/11/29
 * @description ：
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan("com.itheima.health.dao")
public class HealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthApplication.class, args);
    }

}