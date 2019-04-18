package com.sherlock.mallcommon.kafka.schedule;

import com.alibaba.fastjson.JSON;
import com.sherlock.mallcommon.kafka.bean.UserLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;

/**
 * Copyright (C), 2015-2019
 * FileName: TaskScheduled
 * Author:   jcj
 * Date:     2019/4/11 14:11
 * Description:
 */
//@Component
@Slf4j
public class TaskScheduled {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Scheduled(fixedRate = 5000)
    public void productKafkaMsg(){
        UserLog userLog = new UserLog();
        String userId=String.valueOf((new Random().nextInt(100)));
        userLog.setUserName("jhp").setUserId(userId).setState("0");
        log.info("发送用户日志数据:"+userLog);
        kafkaTemplate.send("user-log", JSON.toJSONString(userLog));
    }
}
