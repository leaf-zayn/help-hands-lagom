package com.ysy.project.provider.impl;


import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import com.ysy.project.provider.api.ProviderService;
import org.pcollections.PSequence;
import org.pcollections.TreePVector;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ProviderServiceImpl implements ProviderService {

    private final PersistentEntityRegistry persistentEntityRegistry;

    @Inject
    public ProviderServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
        this.persistentEntityRegistry = persistentEntityRegistry;
        persistentEntityRegistry.register(ProviderEntity.class);
    }

    @Override
    public ServiceCall<NotUsed, String> registerProvider() {
        return request -> {
            PersistentEntityRef<ProviderCommand> ref = persistentEntityRegistry.refFor(ProviderEntity.class, UUID.randomUUID().toString());
            return ref.ask(new ProviderCommand.RegisterProvider("Provider Name", "1234567890", "2023-01-01", "5"))
                    .thenApply(ack -> "Provider registered");
        };
    }

    @Override
    public ServiceCall<NotUsed, PSequence<Provider>> getProviders() {
        return request -> CompletableFuture.completedFuture(TreePVector.empty());
    }
}