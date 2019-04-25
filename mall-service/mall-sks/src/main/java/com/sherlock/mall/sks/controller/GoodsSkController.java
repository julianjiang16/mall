package com.sherlock.mall.sks.controller;

import com.sherlock.mall.sks.sks.SksRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2015-2019
 * FileName: GoodsSkController
 * Author:   jcj
 * Date:     2019/4/25 10:25
 * Description:
 */
@RestController
public class GoodsSkController {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping(value = "/goods",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object sk(){
        Integer store = Integer.valueOf( redisTemplate.opsForValue().get("goods:store:10086").toString());

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10, 10, 3,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i=0;i<5000;i++){
            executor.execute(new SksRunner(redisTemplate));
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return store;
    }
}
