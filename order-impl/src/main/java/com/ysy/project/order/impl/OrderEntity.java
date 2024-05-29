package com.ysy.project.order.impl;


import akka.Done;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;

import java.util.Optional;

public class OrderEntity extends PersistentEntity<OrderCommand, OrderEvent, OrderState> {

    @Override
    public Behavior initialBehavior(Optional<OrderState> snapshotState) {
        BehaviorBuilder b = newBehaviorBuilder(snapshotState.orElse(new OrderState("Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown")));

        b.setCommandHandler(OrderCommand.CreateOrder.class, (cmd, ctx) -> {
            OrderState newState = new OrderState(cmd.getServiceId(), cmd.getConsumerId(), cmd.getProviderId(), cmd.getTimeSlot(), cmd.getCost(), cmd.getStatus(), cmd.getRating());
            return ctx.thenPersist(new OrderEvent.OrderCreated(newState.getServiceId(), newState.getConsumerId(), newState.getProviderId(), newState.getTimeSlot(), newState.getCost(), newState.getStatus(), newState.getRating()), evt -> ctx.reply("Order created successfully"));
        });

        b.setEventHandler(OrderEvent.OrderCreated.class, evt -> new OrderState(evt.getServiceId(), evt.getConsumerId(), evt.getProviderId(), evt.getTimeSlot(), evt.getCost(), evt.getStatus(), evt.getRating()));

        return b.build();
    }
}
