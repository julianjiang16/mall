package com.sherlock.mallgateway.handler;

/**
 * Copyright (C), 2015-2019
 * FileName: BaseHandler
 * Author:   jcj
 * Date:     2019/4/23 9:37
 * Description:
 */
public class BaseHandler {
    protected GatewayHandler gatewayHandler;

    public void setNextHandler(GatewayHandler handler) {
        this.gatewayHandler = handler;
    }
    protected void doNext(){
        if (this.gatewayHandler != null){
            this.gatewayHandler.handler();
        }
    }
}
