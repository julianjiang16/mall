package com.sherlock.mallgateway.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2015-2019
 * FileName: ValidateTokenHandler
 * Author:   jcj
 * Date:     2019/4/23 9:08
 * Description:
 */
@Component
@Slf4j
public class ValidateTokenHandler extends BaseHandler implements GatewayHandler{

    @Override
    public void handler() {
        log.info("validate token handler " );
        doNext();
    }

}
