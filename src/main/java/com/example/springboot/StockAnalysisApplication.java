package com.example.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan( basePackages = "com.example.springboot")
public class StockAnalysisApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockAnalysisApplication.class, args);
    }
}
