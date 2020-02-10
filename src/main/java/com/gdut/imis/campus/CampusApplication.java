package com.gdut.imis.campus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gdut.imis.campus.mapper")
public class CampusApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusApplication.class, args);
    }

}
