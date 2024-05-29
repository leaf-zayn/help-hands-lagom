package com.ysy.project.consumer.impl;

import akka.Done;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;

import java.util.Optional;

public class ConsumerEntity extends PersistentEntity<ConsumerCommand, ConsumerEvent, ConsumerState> {

    @Override
    public Behavior initialBehavior(Optional<ConsumerState> snapshotState) {
        BehaviorBuilder b = newBehaviorBuilder(snapshotState.orElse(new ConsumerState("Unknown", "Unknown", "Unknown", "Unknown", "Unknown")));

        b.setCommandHandler(ConsumerCommand.RegisterConsumer.class, (cmd, ctx) -> {
            ConsumerState newState = new ConsumerState(cmd.getName(), cmd.getAddress(), cmd.getMobile(), cmd.getEmail(), cmd.getGeoLocation());
            return ctx.thenPersist(new ConsumerEvent.ConsumerRegistered(newState.getName(), newState.getAddress(), newState.getMobile(), newState.getEmail(), newState.getGeoLocation()), evt -> ctx.reply("Consumer registered successfully"));
        });

        b.setEventHandler(ConsumerEvent.ConsumerRegistered.class, evt -> new ConsumerState(evt.getName(), evt.getAddress(), evt.getMobile(), evt.getEmail(), evt.getGeoLocation()));

        return b.build();
    }
}
