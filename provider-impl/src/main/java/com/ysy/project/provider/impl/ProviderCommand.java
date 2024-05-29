package com.ysy.project.provider.impl;


import akka.Done;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;

public interface ProviderCommand {
    @SuppressWarnings("serial")
    @JsonDeserialize
    class RegisterProvider implements ProviderCommand, PersistentEntity.ReplyType<Done> {
        private final String name;
        private final String mobile;
        private final String activeSince;
        private final String overallRating;

        @JsonCreator
        public RegisterProvider(String name, String mobile, String activeSince, String overallRating) {
            this.name = name;
            this.mobile = mobile;
            this.activeSince = activeSince;
            this.overallRating = overallRating;
        }

        public String getName() {
            return name;
        }

        public String getMobile() {
            return mobile;
        }

        public String getActiveSince() {
            return activeSince;
        }

        public String getOverallRating() {
            return overallRating;
        }
    }
}
