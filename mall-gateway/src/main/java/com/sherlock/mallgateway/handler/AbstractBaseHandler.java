package com.sherlock.mallgateway.handler;

/**
 * Copyright (C), 2015-2019
 * FileName: BaseHandler
 * Author:   jcj
 * Date:     2019/4/23 9:37
 * Description:
 */
public abstract class AbstractBaseHandler implements GatewayHandler {

    protected GatewayHandler gatewayHandler;

    protected abstract void filter();

    @Override
    public void handler() {
        this.filter();
        this.doNext();
    }

    public void setNextHandler(GatewayHandler handler) {
        this.gatewayHandler = handler;
    }
    protected void doNext(){
        if (this.gatewayHandler != null){
            this.gatewayHandler.handler();
        }
    }
}
