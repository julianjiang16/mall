package com.sherlock.mallgateway.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2015-2019
 * FileName: ValidateAuthorityHandler
 * Author:   jcj
 * Date:     2019/4/23 10:14
 * Description:
 */
@Component
@Slf4j
public class ValidateAuthorityHandler extends BaseHandler implements GatewayHandler {
    @Override
    public void handler() {
        log.info("validate authority handler " );
        doNext();
    }
}
