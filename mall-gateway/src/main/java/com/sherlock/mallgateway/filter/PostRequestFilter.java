package com.sherlock.mallgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sherlock.mallcommon.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * Copyright (C), 2015-2019
 * FileName: PostRequestFilter
 * Author:   jcj
 * Date:     2019/4/17 11:16
 * Description:
 */
@Slf4j
@Component
public class PostRequestFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        Long start = (Long) requestContext.get("start");
        String uri = request.getRequestURI();
        long duration = System.currentTimeMillis() - start;
        log.info("uri: {} , params: {} , duration: {} ms", uri, getParamString(request.getParameterMap()), duration / 100);
        return null;
    }

    private String getParamString(Map<String, String[]> map) {
        if (CollectionUtils.isEmpty(map)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String[]> e : map.entrySet()) {
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if (value != null && value.length == 1) {
                sb.append(value[0]).append(CommonConstant.AND_SEPARATOR);
            } else {
                sb.append(Arrays.toString(value)).append(CommonConstant.AND_SEPARATOR);
            }
        }
        return sb.toString();
    }
}
