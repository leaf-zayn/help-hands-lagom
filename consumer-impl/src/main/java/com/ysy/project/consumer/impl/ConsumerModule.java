package com.ysy.project.consumer.impl;


import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.ysy.project.consumer.api.ConsumerService;

public class ConsumerModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindService(ConsumerService.class, ConsumerServiceImpl.class);
    }
}
