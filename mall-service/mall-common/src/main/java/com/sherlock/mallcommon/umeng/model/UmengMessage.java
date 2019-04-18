package com.sherlock.mallcommon.umeng.model;


import com.sherlock.mallcommon.enums.FollowUpOperationEnum;
import com.sherlock.mallcommon.umeng.push.AndroidNotification;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2015-2018,
 * FileName: UmengMessage
 * Author:   jcj
 * Date:     2018/11/5 15:04
 * Description: 友盟消息模型
 */
public class UmengMessage {

    /** 通知栏提示文字 */
    private String ticker;

    /** 通知标题 */
    private String title;

    /** 通知文字描述 小标题 */
    private String text;

    /** 如果不填写此参数，默认为3天后过期 */
    private String expireTime;

    /** 发送消息描述 内容 */
    private String desc;

    /** 产品模式：测试false、正式true */
    private boolean productionMode;

    /** 通知/消息 */
    private AndroidNotification.DisplayType display;

    /** 后续操作  不为空*/
    private FollowUpOperationEnum operation;

    /** 扩展字段，指定发送者 规定key为sender */
    private Map<String,String> extraField;

    /** 打开app时，该字段为空 */
    private String urlOrActivity;

    /** 幂等判断 */
    private String outBizNo;

    /** 标签，用于groupcast场景 */
    private List<String> tags;

    /** unicast场景下需要，接收者 */
    private String receiver;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getOutBizNo() {
        return outBizNo;
    }

    public void setOutBizNo(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    public String getUrlOrActivity() {
        return urlOrActivity;
    }

    public void setUrlOrActivity(String urlOrActivity) {
        this.urlOrActivity = urlOrActivity;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isProductionMode() {
        return productionMode;
    }

    public void setProductionMode(boolean productionMode) {
        this.productionMode = productionMode;
    }

    public AndroidNotification.DisplayType getDisplay() {
        return display;
    }

    public void setDisplay(AndroidNotification.DisplayType display) {
        this.display = display;
    }

    public FollowUpOperationEnum getOperation() {
        return operation;
    }

    public void setOperation(FollowUpOperationEnum operation) {
        this.operation = operation;
    }

    public Map<String, String> getExtraField() {
        return extraField;
    }

    public void setExtraField(Map<String, String> extraField) {
        this.extraField = extraField;
    }

    @Override
    public String toString() {
        return "UmengMessage{" +
                "ticker='" + ticker + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", expireTime='" + expireTime + '\'' +
                ", desc='" + desc + '\'' +
                ", productionMode=" + productionMode +
                ", display=" + display +
                ", operation=" + operation +
                ", extraField=" + extraField +
                ", urlOrActivity='" + urlOrActivity + '\'' +
                ", outBizNo='" + outBizNo + '\'' +
                ", tags=" + tags +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
