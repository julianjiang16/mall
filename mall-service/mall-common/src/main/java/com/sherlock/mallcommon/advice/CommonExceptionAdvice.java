package com.sherlock.mallcommon.advice;

import com.sherlock.mallcommon.exception.MallException;
import com.sherlock.mallcommon.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (C), 2015-2019
 * FileName: CommonExceptionAdvice
 * Author:   jcj
 * Date:     2019/4/17 14:29
 * Description:
 */
@RestControllerAdvice
public class CommonExceptionAdvice {

    @ExceptionHandler(value = MallException.class)
    public CommonResponse<String> handlerException(HttpServletRequest request, MallException exception){
        CommonResponse<String> response =new CommonResponse<>(exception.getCode(),exception.getErrorMsg(),exception.getErrorObjInfo());
        return response;
    }
}
