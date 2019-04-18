package com.sherlock.mallcommon.kafka.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserLog {
    private String userName;
    private String userId;
    private String state;
}