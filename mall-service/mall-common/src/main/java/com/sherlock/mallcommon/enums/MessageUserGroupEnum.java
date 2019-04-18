package com.sherlock.mallcommon.enums;


/**
 * Copyright (C), 2015-2018,
 * FileName: MessageUserGroupEnum
 * Author:   jcj
 * Date:     2018/12/13 10:02
 * Description: 消息推送用户群体
 */
public enum MessageUserGroupEnum {

    /** C端用户 */
    CUSTOMER_USER_GROUP("01", "CUSTOMER_USER_GROUP", "C端用户"),

    /** B端用户 */
    BUSINESS_USER_GROUP("02", "BUSINESS_USER_GROUP", "B端用户");

    MessageUserGroupEnum(String code, String englishName, String desc) {
        this.code = code;
        this.englishName = englishName;
        this.desc = desc;
    }

    public static MessageUserGroupEnum getByCode(String code){
        for (MessageUserGroupEnum e:MessageUserGroupEnum.values()){
            if (e.getCode().equals(code)){
                return e;
            }
        }
        return null;
    }

    private String code;

    private String englishName;

    private String desc;

    public String getCode() {
        return code;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getDesc() {
        return desc;
    }
}
