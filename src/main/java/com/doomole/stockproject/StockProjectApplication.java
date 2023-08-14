package com.doomole.stockproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages= {"com.doomole.stockproject.repository"}, sqlSessionFactoryRef="sqlSessionFactory")
@SpringBootApplication
public class StockProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockProjectApplication.class, args);
    }

}
