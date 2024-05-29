package com.ysy.project.provider.impl;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.persistence.AggregateEvent;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;

public interface ProviderEvent extends AggregateEvent<ProviderEvent> {
    AggregateEventTag<ProviderEvent> TAG = AggregateEventTag.of(ProviderEvent.class);

    @Override
    default AggregateEventTag<ProviderEvent> aggregateTag() {
        return TAG;
    }

    @SuppressWarnings("serial")
    @JsonDeserialize
    class ProviderRegistered implements ProviderEvent {
        private final String name;
        private final String mobile;
        private final String activeSince;
        private final String overallRating;

        @JsonCreator
        public ProviderRegistered(String name, String mobile, String activeSince, String overallRating) {
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
