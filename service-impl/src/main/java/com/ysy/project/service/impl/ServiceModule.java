package com.ysy.project.service.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.ysy.project.service.api.ServiceService;

public class ServiceModule extends AbstractModule implements ServiceGuiceSupport {

    @Override
    protected void configure() {
        bindService(ServiceService.class, ServiceServiceImpl.class);
    }
}
