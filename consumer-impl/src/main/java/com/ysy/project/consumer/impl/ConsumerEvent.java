package com.ysy.project.consumer.impl;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.persistence.AggregateEvent;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;
import com.lightbend.lagom.serialization.Jsonable;

public interface ConsumerEvent extends Jsonable, AggregateEvent<ConsumerEvent> {
    AggregateEventTag<ConsumerEvent> TAG = AggregateEventTag.of(ConsumerEvent.class);

    @Override
    default AggregateEventTag<ConsumerEvent> aggregateTag() {
        return TAG;
    }

    @SuppressWarnings("serial")
    @JsonDeserialize
    class ConsumerRegistered implements ConsumerEvent {
        private final String name;
        private final String address;
        private final String mobile;
        private final String email;
        private final String geoLocation;

        @JsonCreator
        public ConsumerRegistered(String name, String address, String mobile, String email, String geoLocation) {
            this.name = name;
            this.address = address;
            this.mobile = mobile;
            this.email = email;
            this.geoLocation = geoLocation;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getMobile() {
            return mobile;
        }

        public String getEmail() {
            return email;
        }

        public String getGeoLocation() {
            return geoLocation;
        }
    }
}
