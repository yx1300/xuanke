package com.xiao.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.xiao.boot.mapper")
@SpringBootApplication
public class XuankeApplication {

    public static void main(String[] args) {
        SpringApplication.run(XuankeApplication.class, args);
    }

}
