package com.sherlock.mallgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.sherlock.mallgateway.handler.GatewayHandler;
import org.springframework.beans.BeansException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2015-2019
 * FileName: GatewayFilter
 * Author:   jcj
 * Date:     2019/4/23 9:40
 * Description:
 */
@Component
public class GatewayFilter extends ZuulFilter implements ApplicationContextAware, EnvironmentAware {

    private ApplicationContext applicationContext;

    private Environment environment;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        GatewayHandler handler1 = (GatewayHandler) this.applicationContext.getBean("rateLimitHandler");
        GatewayHandler handler2 = (GatewayHandler) this.applicationContext.getBean("validateTokenHandler");
        GatewayHandler handler3 = (GatewayHandler) this.applicationContext.getBean("validateAuthorityHandler");
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);
        handler1.handler();
        System.err.println(this.environment.getProperty("gateway.rule.json"));
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
