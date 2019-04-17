package com.sherlock.mallgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2015-2019
 * FileName: PreRequestFilter
 * Author:   jcj
 * Date:     2019/4/17 11:12
 * Description: 简单日志打印
 */
@Component
@Slf4j
public class PreRequestFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 永远执行
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 1. 获取当前上下文
        RequestContext requestContext = RequestContext.getCurrentContext();

        requestContext.set("start",System.currentTimeMillis());
        return null;
    }
}
