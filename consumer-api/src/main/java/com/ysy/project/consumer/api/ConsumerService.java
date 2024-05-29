package com.ysy.project.consumer.api;


import static com.lightbend.lagom.javadsl.api.Service.*;


import akka.NotUsed;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import org.pcollections.PSequence;

import static com.lightbend.lagom.javadsl.api.transport.Method.POST;

public interface ConsumerService extends Service {

    ServiceCall<NotUsed, String> registerConsumer();
    ServiceCall<NotUsed, PSequence<Consumer>> getConsumers();

    @Override
    default Descriptor descriptor() {
        return named("service-consumer")
                .withCalls(
                        restCall(POST, "/api/consumer/register", this::registerConsumer),
                        pathCall("/api/consumers", this::getConsumers)
                )
                .withAutoAcl(true);
    }

    @JsonDeserialize
    final class Consumer {
        public final String id;
        public final String name;
        public final String address;
        public final String mobile;
        public final String email;
        public final String geoLocation;

        public Consumer(String id, String name, String address, String mobile, String email, String geoLocation) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.mobile = mobile;
            this.email = email;
            this.geoLocation = geoLocation;
        }
    }
}
