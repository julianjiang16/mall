package com.sherlock.mallcommon.enums;


/**
 * Copyright (C), 2015-2019,
 * FileName: MallErrorEnum
 * Author:   jcj
 * Date:     2019/4/17 14:22
 * Description:
 */
public enum MallErrorEnum {

    /** 访问成功 */
    SUCCESS_ACCESS("20000", "SUCCESS_ACCESS", "访问成功", "访问成功"),

    /** 错误的请求参数 */
    ERROR_REQUEST_PARAMS("20001", "ERROR_REQUEST_PARAMS", "错误的请求参数", "错误的请求参数"),

    /** 主键冲突异常 */
    DOUBLE_KEY_ERROR("20002", "DOUBLE_KEY_ERROR", "主键冲突错误", "主键冲突错误"),

    /** android 推送消息填充参数,缺少参数错误 */
    PUSH_ANDROID_MESSAGE_MISSING_PARAM_ERROR("20003", "PUSH_ANDROID_MESSAGE_MISSING_PARAM_ERROR", "android 推送消息填充参数,缺少参数错误", "umeng push android message,fill params error,missing key."),

    /** android 推送消息填充参数错误 */
    PUSH_ANDROID_MESSAGE_PARAM_FILL_ERROR("20004", "PUSH_ANDROID_MESSAGE_PARAM_FILL_ERROR", "android 推送消息填充参数错误", "You don't need to set value for key , just set values for the sub keys in it."),

    /** android 推送消息填充参数,未知参数错误 */
    PUSH_ANDROID_MESSAGE_UNKNOWN_PARAM_ERROR("20005", "PUSH_ANDROID_MESSAGE_UNKNOWN_PARAM_ERROR", "android 推送消息填充参数,未知参数错误", "umeng push android message,fill params error,unknown key."),

    /** ios 推送消息填充参数错误 */
    PUSH_IOS_MESSAGE_PARAM_FILL_ERROR("20006", "PUSH_IOS_MESSAGE_PARAM_FILL_ERROR", "ios 推送消息填充参数错误", "You don't need to set value for key , just set values for the sub keys in it."),

    /** ios 推送消息填充参数,未知参数错误 */
    PUSH_IOS_MESSAGE_UNKNOWN_PARAM_ERROR("20007", "PUSH_IOS_MESSAGE_UNKNOWN_PARAM_ERROR", "ios 推送消息填充参数,未知参数错误", "umeng push ios message,fill params error,unknown key."),

    /** umeng消息推送失败 */
    PUSH_UMENG_MESSAGE_FAIL_ERROR("20008", "PUSH_UMENG_MESSAGE_FAIL_ERROR", "umeng消息推送失败", "umeng push message fail error."),

    /** 服务调用异常 */
    SERVICE_INVOKE_ERROR("50000","SERVICE_INVOKE_ERROR", "服务调用异常", "service invoke error")
    ;

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
