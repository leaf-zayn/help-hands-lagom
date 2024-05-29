package com.ysy.project.consumer.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import com.ysy.project.consumer.api.ConsumerService;
import org.pcollections.PSequence;
import org.pcollections.TreePVector;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ConsumerServiceImpl implements ConsumerService {

    private final PersistentEntityRegistry persistentEntityRegistry;

    @Inject
    public ConsumerServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
        this.persistentEntityRegistry = persistentEntityRegistry;
    }


    @Override
    public ServiceCall<NotUsed, String> registerConsumer() {
        return request -> {
            PersistentEntityRef<ConsumerCommand> ref = persistentEntityRegistry.refFor(ConsumerEntity.class, UUID.randomUUID().toString());
            return ref.ask(new ConsumerCommand.RegisterConsumer("John Doe", "123 Main St", "1234567890", "john.doe@example.com", "SomeLocation"))
                    .thenApply(done -> "Consumer registered successfully");
        };
    }

    @Override
    public ServiceCall<NotUsed, PSequence<Consumer>> getConsumers() {
        return request -> CompletableFuture.completedFuture(TreePVector.empty());
    }
}
