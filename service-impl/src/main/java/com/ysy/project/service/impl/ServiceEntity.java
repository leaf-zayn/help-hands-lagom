package com.ysy.project.service.impl;

import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import akka.Done;

import java.util.Optional;

public class ServiceEntity extends PersistentEntity<ServiceCommand, ServiceEvent, ServiceState> {

    @Override
    public Behavior initialBehavior(Optional<ServiceState> snapshotState) {
        BehaviorBuilder b = newBehaviorBuilder(snapshotState.orElse(new ServiceState("Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown")));

        b.setCommandHandler(ServiceCommand.RegisterService.class, (cmd, ctx) -> {
            ServiceState newState = new ServiceState(cmd.getName(), cmd.getType(), cmd.getProviderId(), cmd.getServiceArea(), cmd.getHourlyCost(), cmd.getGeoLocationBoundary(), cmd.getOverallRating(), cmd.getStatus());
            return ctx.thenPersist(new ServiceEvent.ServiceRegistered(newState.getName(), newState.getType(), newState.getProviderId(), newState.getServiceArea(), newState.getHourlyCost(), newState.getGeoLocationBoundary(), newState.getOverallRating(), newState.getStatus()), evt -> ctx.reply("Service registered successfully"));
        });

        b.setEventHandler(ServiceEvent.ServiceRegistered.class, evt -> new ServiceState(evt.getName(), evt.getType(), evt.getProviderId(), evt.getServiceArea(), evt.getHourlyCost(), evt.getGeoLocationBoundary(), evt.getOverallRating(), evt.getStatus()));

        return b.build();
    }
}
