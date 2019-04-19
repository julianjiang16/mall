package com.sherlock.mallpayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Copyright (C), 2015-2019
 * FileName: PaymentApplication
 * Author:   jcj
 * Date:     2019/4/19 9:21
 * Description:
 */
@EnableAutoConfiguration
@ComponentScan("com.sherlock.*")
@EnableEurekaClient
@EnableDiscoveryClient
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class,args);
    }
}
