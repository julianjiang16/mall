package com.sherlock.mallcommon.enums;


/**
 * Copyright (C), 2015-2019, 大胜百一网络科技
 * FileName: MallErrorEnum
 * Author:   jcj
 * Date:     2019/4/17 14:22
 * Description:
 */
public enum MallErrorEnum {



    /** 主键冲突异常 */
    DOUBLE_KEY_ERROR("20001", "DOUBLE_KEY_ERROR", "主键冲突错误", "主键冲突错误");

    String code;
    String englishName;
    String chinaName;
    String description;

    MallErrorEnum(String code, String englishName, String chinaName, String description) {
        this.code = code;
        this.englishName = englishName;
        this.chinaName = chinaName;
        this.description = description;
    }

    /**
     * 功能描述: <br> 根据code获取枚举
     * @version: 1.0.0
     * @Author: jcj
     * @Date: 2019/4/17 14:25
     */
    public MallErrorEnum getByCode(String code) {
        for (MallErrorEnum e : MallErrorEnum.values()) {
            if (e.code.equals(code)) {
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

    public String getChinaName() {
        return chinaName;
    }

    public String getDescription() {
        return description;
    }
}
