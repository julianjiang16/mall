package com.sherlock.mallcommon.kafka.producer;

import com.alibaba.fastjson.JSON;
import com.sherlock.mallcommon.kafka.bean.UserLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserLogProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;
 
    /**
     * 发送数据
     * @param userId
     */
    public void sendLog(String userId){
        UserLog userLog = new UserLog();
        userLog.setUserName("jhp").setUserId(userId).setState("0");
        log.info("发送用户日志数据:"+userLog);
        System.err.println("发送用户日志数据:"+userLog);
        kafkaTemplate.send("user-log", JSON.toJSONString(userLog));
    }
}