package com.ysy.project.consumer.impl;


import akka.Done;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.serialization.Jsonable;

public interface ConsumerCommand extends Jsonable {
    @SuppressWarnings("serial")
    @JsonDeserialize
    class RegisterConsumer implements ConsumerCommand, PersistentEntity.ReplyType<String> {
        private final String name;
        private final String address;
        private final String mobile;
        private final String email;
        private final String geoLocation;

        @JsonCreator
        public RegisterConsumer(String name, String address, String mobile, String email, String geoLocation) {
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
