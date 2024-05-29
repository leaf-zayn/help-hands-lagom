package com.ysy.project.service.impl;


import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import com.ysy.project.service.api.ServiceService;
import org.pcollections.PSequence;
import org.pcollections.TreePVector;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


public class ServiceServiceImpl implements ServiceService {

    private final PersistentEntityRegistry persistentEntityRegistry;

    @Inject
    public ServiceServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
        this.persistentEntityRegistry = persistentEntityRegistry;
        persistentEntityRegistry.register(ServiceEntity.class);
    }

    @Override
    public ServiceCall<NotUsed, String> registerService() {
        return request -> {
            PersistentEntityRef<ServiceCommand> ref = persistentEntityRegistry.refFor(ServiceEntity.class, UUID.randomUUID().toString());
            return ref.ask(new ServiceCommand.RegisterService("Service Name", "Type", "Provider ID", "Service Area", "100", "Boundary", "5", "Active"));
        };
    }

    @Override
    public ServiceCall<NotUsed, PSequence<ServiceInfo>> getServices() {
        return request -> CompletableFuture.completedFuture(TreePVector.empty());
    }
}
