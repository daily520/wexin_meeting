package com.qfjy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class DaixuWeixinApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaixuWeixinApplication.class, args);
    }

}
