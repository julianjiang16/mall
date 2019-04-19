package com.sherlock.mallpayment.enums;

/**
 * Copyright (C), 2015-2019
 * FileName: OrderStatusEnums
 * Author:   jcj
 * Date:     2019/4/19 10:18
 * Description:
 */
public enum  OrderStatusEnums {

    // 01-初始化；02-支付中；03：支付成功；04：支付失败
    /** 默认支付状态 */
    DEFAULT_ORDER_STATUS("01","DEFAULT_ORDER_STATUS","默认支付状态"),

    ORDER_STATUS_PAYING("02","ORDER_STATUS_PAYING","发起支付申请，支付中"),

    ORDER_STATUS_SUCCESS("03","ORDER_STATUS_SUCCESS","支付成功"),

    ORDER_STATUS_FAIL("04","ORDER_STATUS_FAIL","支付失败"),

    ;

    OrderStatusEnums(String code, String englishName, String desc) {
        this.code = code;
        this.englishName = englishName;
        this.desc = desc;
    }

    public static OrderStatusEnums getByCode(String code){
        for (OrderStatusEnums e:OrderStatusEnums.values()){
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
