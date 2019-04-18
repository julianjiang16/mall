package com.sherlock.mallcommon.redis.controller;

import com.alibaba.fastjson.JSONObject;
import com.sherlock.mallcommon.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C), 2015-2019
 * FileName: RedisController
 * Author:   jcj
 * Date:     2019/4/12 10:08
 * Description:
 */
@RestController
@RequestMapping("/rank/")
public class RedisController {

    @Autowired
    RedisUtils redisUtils;

    // 为了简单就直接用get
    @GetMapping("integral")
    public JSONObject integral(String userId,Double integral){
        JSONObject json=new JSONObject();
        redisUtils.update("student:rank", userId, integral);
        return json;
    }

    @GetMapping("num")
    public JSONObject integral(String userId,int top){
        JSONObject json=new JSONObject();
        json.put("rank",redisUtils.getRank("student:rank", top));
        json.put("ranking", redisUtils.getRanking("student:rank", userId ));
        return json;
    }
}
