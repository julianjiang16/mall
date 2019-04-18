package com.sherlock.mallcommon.enums;


/**
 * Copyright (C), 2015-2018,
 * FileName: FollowUpOperationEnum
 * Author:   jcj
 * Date:     2018/11/5 15:14
 * Description: 后续操作枚举类
 */
public enum FollowUpOperationEnum {

    /** 打开app */
    OPEN_APP("01","OPEN_APP","打开app"),

    /** 链接url */
    GO_TO_URL("02","GO_TO_URL","链接url"),

    /** 指定页面 */
    GO_TO_ACTIVITY("03","GO_TO_ACTIVITY","指定页面"),

    /** 其他操作 */
    OTHER_OPERATION("04","OTHER_OPERATION","自定义");

    FollowUpOperationEnum(String code, String englishName, String desc) {
        this.code = code;
        this.englishName = englishName;
        this.desc = desc;
    }

    public static FollowUpOperationEnum getByCode(String code){
        for (FollowUpOperationEnum e:FollowUpOperationEnum.values()){
            if (e.getCode().equals(code)){
                return e;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getDesc() {
        return desc;
    }

    private String code;

    private String englishName;

    private String desc;
}
