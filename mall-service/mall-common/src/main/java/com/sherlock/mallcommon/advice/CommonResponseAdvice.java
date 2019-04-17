package com.sherlock.mallcommon.advice;

import com.sherlock.mallcommon.anno.Ignore;
import com.sherlock.mallcommon.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Copyright (C), 2015-2019
 * FileName: CommonResponseAdvice
 * Author:   jcj
 * Date:     2019/4/17 14:10
 * Description:
 */
@RestControllerAdvice
public class CommonResponseAdvice  implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if (methodParameter.getDeclaringClass().isAnnotationPresent(Ignore.class)
            || methodParameter.getMethod().isAnnotationPresent(Ignore.class)){
            return false;
        }
        return true;
    }

    @Override
    public CommonResponse beforeBodyWrite(Object object, MethodParameter methodParameter,
                                          MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
                                          ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        CommonResponse response = new CommonResponse();
        if (null == object){
            return response;
        }
        if (object instanceof CommonResponse){
            return (CommonResponse)object;
        }
        response.setData(object);
        return response;
    }
}
