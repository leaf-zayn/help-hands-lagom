package com.ysy.project.service.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.serialization.Jsonable;
import lombok.Getter;

public interface ServiceCommand extends Jsonable {
    @Getter
    @SuppressWarnings("serial")
    @JsonDeserialize
    class RegisterService implements ServiceCommand, PersistentEntity.ReplyType<String> {
        private final String name;
        private final String type;
        private final String providerId;
        private final String serviceArea;
        private final String hourlyCost;
        private final String geoLocationBoundary;
        private final String overallRating;
        private final String status;

        @JsonCreator
        public RegisterService(String name, String type, String providerId, String serviceArea, String hourlyCost, String geoLocationBoundary, String overallRating, String status) {
            this.name = name;
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
