package com.ysy.project.provider.api;


import static com.lightbend.lagom.javadsl.api.Service.*;

import akka.NotUsed;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import org.pcollections.PSequence;

import static com.lightbend.lagom.javadsl.api.transport.Method.POST;

public interface ProviderService extends Service {

    ServiceCall<NotUsed, String> registerProvider();
    ServiceCall<NotUsed, PSequence<Provider>> getProviders();

    @Override
    default Descriptor descriptor() {
        return named("service-provider")
                .withCalls(
                        restCall(POST, "/api/provider/register", this::registerProvider),
                        pathCall("/api/providers", this::getProviders)
                )
                .withAutoAcl(true);
    }

    @JsonDeserialize
    final class Provider {
        public final String id;
        public final String name;
        public final String mobile;
        public final String activeSince;
        public final String overallRating;

        public Provider(String id, String name, String mobile, String activeSince, String overallRating) {
            this.id = id;
            this.name = name;
            this.mobile = mobile;
            this.activeSince = activeSince;
            this.overallRating = overallRating;
        }
    }
}
