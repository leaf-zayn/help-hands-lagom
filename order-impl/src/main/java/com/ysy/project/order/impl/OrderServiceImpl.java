package com.ysy.project.order.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.Offset;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import com.ysy.project.order.api.OrderInfo;
import com.ysy.project.order.api.OrderService;
import org.pcollections.PSequence;
import org.pcollections.TreePVector;
import akka.stream.Materializer;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.CompletionStage;


public class OrderServiceImpl implements OrderService {

    private final PersistentEntityRegistry persistentEntityRegistry;
    private final Materializer materializer;

    @Inject
    public OrderServiceImpl(PersistentEntityRegistry persistentEntityRegistry, Materializer materializer) {
        this.persistentEntityRegistry = persistentEntityRegistry;
        this.materializer = materializer;
        persistentEntityRegistry.register(OrderEntity.class);
    }

    @Override
    public ServiceCall<OrderInfo, String> createOrder() {
        return orderInfo -> {
            String orderId = UUID.randomUUID().toString();
            PersistentEntityRef<OrderCommand> ref = persistentEntityRegistry.refFor(OrderEntity.class, orderId);
            return ref.ask(new OrderCommand.CreateOrder(
                    orderInfo.serviceId,
                    orderInfo.consumerId,
                    orderInfo.providerId,
                    orderInfo.timeSlot,
                    orderInfo.cost,
                    orderInfo.status,
                    orderInfo.rating
            )).thenApply(done -> orderId);
        };
    }

    @Override
    public ServiceCall<NotUsed, PSequence<OrderInfo>> getOrders() {
        return request -> {
            CompletionStage<PSequence<OrderInfo>> ordersFuture =
                    persistentEntityRegistry.eventStream(OrderEvent.TAG, Offset.NONE)
                            .runFold(TreePVector.empty(), (acc, eventAndOffset) -> {
                                OrderEvent event = eventAndOffset.first();
                                if (event instanceof OrderEvent.OrderCreated) {
                                    OrderEvent.OrderCreated orderCreated = (OrderEvent.OrderCreated) event;
                                    OrderInfo orderInfo = new OrderInfo(
                                            UUID.randomUUID().toString(),
                                            orderCreated.getServiceId(), orderCreated.getConsumerId(),
                                            orderCreated.getProviderId(), orderCreated.getTimeSlot(), orderCreated.getCost(),
                                            orderCreated.getStatus(), orderCreated.getRating()
                                    );
                                    return acc.plus(orderInfo);
                                } else {
                                    return acc;
                                }
                            }, materializer);

            return ordersFuture;
        };
    }
}

