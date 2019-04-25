package com.sherlock.mall.sks.sks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;

/**
 * Copyright (C), 2015-2019
 * FileName: SksRunner
 * Author:   jcj
 * Date:     2019/4/25 11:00
 * Description:
 */
@Slf4j
public class SksRunner implements Runnable {

    private RedisTemplate redisTemplate;

    public SksRunner(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static final String GOODS_KEY = "goods:store:10086";
    private static final String GOODS_USER_KEY = "goods:user:id";

    @Override
    public void run() {
        Long result = Long.valueOf(redisTemplate.opsForValue().get(GOODS_KEY).toString());
        if (result <= 0) {
            log.info("卖完了，当前库存：{}", result);
            return;
        }
        // 获取用户id
        String userId = UUID.randomUUID().toString();
        redisTemplate.opsForValue().increment(GOODS_KEY,-1);
        redisTemplate.opsForList().leftPush(GOODS_USER_KEY, userId);
        log.info("用户{}抢到了商品", userId);
    }
}
