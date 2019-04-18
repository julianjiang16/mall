package com.sherlock.mallcommon.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (C), 2015-2019
 * FileName: RedisUtils
 * Author:   jcj
 * Date:     2019/4/10 9:39
 * Description:
 */

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public void update(String key,String obj,Double score){
        // 参数非空校验
        //实际操作
        redisTemplate.opsForZSet().incrementScore(key,obj,score);
    }

    public List getRank(String key,int top){
        List result=redisTemplate.opsForZSet().reverseRangeWithScores(key,0 , top).stream().collect(Collectors.toList());
        return result;
    }

    public Long getRanking(String key,String obj){
        return redisTemplate.opsForZSet().reverseRank(key,obj)+1;
    }
}
