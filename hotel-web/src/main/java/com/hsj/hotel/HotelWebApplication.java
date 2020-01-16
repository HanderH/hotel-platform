package com.hsj.hotel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hsj.hotel.mapper")
public class HotelWebApplication {

    public static void main(String[] args) {

        SpringApplication.run(HotelWebApplication.class,args);
    }
}
