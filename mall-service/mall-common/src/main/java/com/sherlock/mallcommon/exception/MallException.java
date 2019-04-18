package com.sherlock.mallcommon.exception;

import com.sherlock.mallcommon.enums.MallErrorEnum;

/**
 * Copyright (C), 2015-2019
 * FileName: MallException
 * Author:   jcj
 * Date:     2019/4/17 14:26
 * Description:
 */
public class MallException  extends Exception  {

    /** 异常代码 */
    private final String code;

    /** 异常信息 */
    private final String errorMsg;

    /** 异常对象信息 */
    private final String errorObjInfo;

    public MallException(MallErrorEnum errorEnum, String errorMsg, String errorObjInfo) {
        super(errorEnum.getDescription());
        this.code = errorEnum.getCode();
        this.errorMsg = errorMsg;
        this.errorObjInfo = errorObjInfo;
    }



    public MallException(MallErrorEnum errorEnum, String errorObjInfo) {
        super(errorEnum.getDescription());
        this.code = errorEnum.getCode();
        this.errorMsg = errorEnum.getChinaName();
        this.errorObjInfo = errorObjInfo;
    }

    public MallException(MallErrorEnum errorEnum) {
        super(errorEnum.getDescription());
        this.code = errorEnum.getCode();
        this.errorMsg = errorEnum.getChinaName();
        this.errorObjInfo = errorEnum.getDescription();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getErrorObjInfo() {
        return errorObjInfo;
    }

    public String getCode() {
        return code;
    }
}
