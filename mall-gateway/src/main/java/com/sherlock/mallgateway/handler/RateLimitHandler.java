package com.sherlock.mallgateway.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2015-2019
 * FileName: RateLimitHandler
 * Author:   jcj
 * Date:     2019/4/23 9:36
 * Description:
 */
@Component
@Slf4j
public class RateLimitHandler extends BaseHandler implements GatewayHandler {
    @Override
    public void handler() {
        log.info("rateLimit handler" );
        doNext();
    }
}
