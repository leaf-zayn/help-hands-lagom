package com.ysy.project.provider.impl;


import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import com.ysy.project.provider.api.ProviderService;

public class ProviderModule extends AbstractModule implements ServiceGuiceSupport {

    @Override
    protected void configure() {
        bindService(ProviderService.class, ProviderServiceImpl.class);
    }
}
