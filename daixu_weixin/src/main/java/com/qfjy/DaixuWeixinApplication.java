package com.qfjy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = "com.qfjy.mapper")
public class DaixuWeixinApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaixuWeixinApplication.class, args);
    }

}
