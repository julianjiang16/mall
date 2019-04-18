package com.sherlock.mallgoods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Copyright (C), 2015-2019
 * FileName: MallGoodsApplication
 * Author:   jcj
 * Date:     2019/4/17 15:03
 * Description:
 */
@EnableFeignClients
@EnableCircuitBreaker
@EnableEurekaClient
@EnableAutoConfiguration
@ComponentScan(value = "com.sherlock.*")
@MapperScan(value = "com.sherlock.mallgoods.mapper")
public class MallGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallGoodsApplication.class,args);
    }
}
