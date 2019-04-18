package com.sherlock.mallcommon.enums;


/**
 * Copyright (C), 2015-2018,
 * FileName: PushMessageEquipmentTypeEnum
 * Author:   jcj
 * Date:     2018/10/24 13:57
 * Description: app推送消息设备类型枚举类
 */
public enum PushMessageEquipmentTypeEnum {

    /** 所有设备 */
    ALL_EQUIPMENT("01","ALL_EQUIPMENT","所有设备"),

    /** IOS */
    IOS_EQUIPMENT("02","IOS_EQUIPMENT","IOS"),

    /** Android */
    ANDROID_EQUIPMENT("03","ANDROID_EQUIPMENT","Android");

    PushMessageEquipmentTypeEnum(String code, String englishName, String desc) {
        this.code = code;
        this.englishName = englishName;
        this.desc = desc;
    }

    /**
     * 功能描述: <br> 通过code获取枚举类
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2018/10/24 13:59
     */
    public static PushMessageEquipmentTypeEnum getByCode(String code){
        for (PushMessageEquipmentTypeEnum e:PushMessageEquipmentTypeEnum.values()){
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
