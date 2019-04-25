package com.sherlock.mallgateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sherlock.mallgateway.handler.GatewayHandler;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

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
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String uri = request.getRequestURI();
        if (StringUtils.isNotEmpty(uri) && uri.contains("sks")){//秒杀接口
            // 根据不同系统 选择不同的网关过滤策略
            // 限流
        }
        // 继续完善
        JSONObject json = JSONObject.parseObject(this.environment.getProperty("gateway.rule.json"));
        if (json == null || StringUtils.isEmpty(json.getString("id"))){
            return null;
        }
        GatewayHandler mainHandler = (GatewayHandler) this.applicationContext.getBean(json.getString("id"));
        JSONObject current = (JSONObject) json.get("children");
        GatewayHandler currentHandler = mainHandler;
        while (current != null){
            // 省略为空校验了
            if (StringUtils.isEmpty(current.getString("id"))){
                break;
            }
            GatewayHandler next = (GatewayHandler) this.applicationContext.getBean(current.getString("id"));
            JSONObject n = current.getJSONObject("children");
            current = n;
            currentHandler.setNextHandler(next);
            currentHandler = next;
        }
        mainHandler.handler();
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
