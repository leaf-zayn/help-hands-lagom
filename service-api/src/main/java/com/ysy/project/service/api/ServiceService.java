package com.ysy.project.service.api;

import static com.lightbend.lagom.javadsl.api.Service.*;

import akka.Done;
import akka.NotUsed;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import org.pcollections.PSequence;


import static com.lightbend.lagom.javadsl.api.transport.Method.POST;

public interface ServiceService extends Service {

    ServiceCall<NotUsed, String> registerService();
    ServiceCall<NotUsed, PSequence<ServiceInfo>> getServices();

    @Override
    default Descriptor descriptor() {
        return named("service")
                .withCalls(
                        restCall(POST, "/api/service/register", this::registerService),
                        pathCall("/api/services", this::getServices)
                )
                .withAutoAcl(true);
    }

    @JsonDeserialize
    final class ServiceInfo {
        public final String id;
        public final String type;
        public final String providerId;
        public final String serviceArea;
        public final String hourlyCost;
        public final String geoLocationBoundary;
        public final String overallRating;
        public final String status;

        public ServiceInfo(String id, String type, String providerId, String serviceArea, String hourlyCost, String geoLocationBoundary, String overallRating, String status) {
            this.id = id;
            this.type = type;
            this.providerId = providerId;
            this.serviceArea = serviceArea;
            this.hourlyCost = hourlyCost;
            this.geoLocationBoundary = geoLocationBoundary;
            this.overallRating = overallRating;
            this.status = status;
        }
    }
}
