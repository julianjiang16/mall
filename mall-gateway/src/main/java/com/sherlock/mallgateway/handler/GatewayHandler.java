package com.sherlock.mallgateway.handler;

/**
 * Copyright (C), 2015-2019,
 * FileName: GatewayHandler
 * Author:   jcj
 * Date:     2019/4/23 9:06
 * Description: 网关处理器
 */
public interface GatewayHandler {

    void handler();

    void setNextHandler(GatewayHandler handler);
}
