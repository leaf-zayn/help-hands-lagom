package com.ysy.project.order.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.ysy.project.order.api.OrderService;

public class OrderModule extends AbstractModule implements ServiceGuiceSupport {

    @Override
    protected void configure() {
        bindService(OrderService.class, OrderServiceImpl.class);
    }
}
