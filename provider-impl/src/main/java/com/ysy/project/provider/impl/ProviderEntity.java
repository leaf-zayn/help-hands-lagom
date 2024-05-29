package com.ysy.project.provider.impl;


import akka.Done;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;

import java.util.Optional;

public class ProviderEntity extends PersistentEntity<ProviderCommand, ProviderEvent, ProviderState> {

    @Override
    public Behavior initialBehavior(Optional<ProviderState> snapshotState) {
        BehaviorBuilder b = newBehaviorBuilder(snapshotState.orElse(new ProviderState("Unknown", "Unknown", "Unknown", "Unknown")));

        b.setCommandHandler(ProviderCommand.RegisterProvider.class, (cmd, ctx) -> {
            ProviderState newState = new ProviderState(cmd.getName(), cmd.getMobile(), cmd.getActiveSince(), cmd.getOverallRating());
            return ctx.thenPersist(new ProviderEvent.ProviderRegistered(newState.getName(), newState.getMobile(), newState.getActiveSince(), newState.getOverallRating()), evt -> ctx.reply(Done.getInstance()));
        });

        b.setEventHandler(ProviderEvent.ProviderRegistered.class, evt -> new ProviderState(evt.getName(), evt.getMobile(), evt.getActiveSince(), evt.getOverallRating()));

        return b.build();
    }
}
