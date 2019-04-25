package com.sherlock.mall.sks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Copyright (C), 2015-2019
 * FileName: SksApplication
 * Author:   jcj
 * Date:     2019/4/25 9:21
 * Description:
 */

@EnableAutoConfiguration
@ComponentScan("com.sherlock.*")
public class SksApplication {

    public static void main(String[] args) {
        SpringApplication.run(SksApplication.class,args);
    }
}
