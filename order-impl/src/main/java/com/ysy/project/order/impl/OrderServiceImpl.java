package com.ysy.project.order.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import com.ysy.project.order.api.OrderInfo;
import com.ysy.project.order.api.OrderService;
import org.pcollections.PSequence;
import org.pcollections.TreePVector;


import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class OrderServiceImpl implements OrderService {

    private final PersistentEntityRegistry persistentEntityRegistry;

    @Inject
    public OrderServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
        this.persistentEntityRegistry = persistentEntityRegistry;
        persistentEntityRegistry.register(OrderEntity.class);
    }

    @Override
    public ServiceCall<NotUsed, String> createOrder() {
        return request -> {
            PersistentEntityRef<OrderCommand> ref = persistentEntityRegistry.refFor(OrderEntity.class, UUID.randomUUID().toString());
            return ref.ask(new OrderCommand.CreateOrder("Service ID", "Consumer ID", "Provider ID", "10:00-12:00", "100", "Pending", "5"));
        };
    }

    @Override
    public ServiceCall<NotUsed, PSequence<OrderInfo>> getOrders() {
        return request -> CompletableFuture.completedFuture(TreePVector.empty());
    }
}
