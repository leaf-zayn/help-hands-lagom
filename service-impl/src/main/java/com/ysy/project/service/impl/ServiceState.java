package com.ysy.project.service.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.serialization.Jsonable;

@JsonDeserialize
public class ServiceState implements Jsonable {
    private final String name;
    private final String type;
    private final String providerId;
    private final String serviceArea;
    private final String hourlyCost;
    private final String geoLocationBoundary;
    private final String overallRating;
    private final String status;

    @JsonCreator
    public ServiceState(String name, String type, String providerId, String serviceArea, String hourlyCost, String geoLocationBoundary, String overallRating, String status) {
        this.name = name;
        this.type = type;
        this.providerId = providerId;
        this.serviceArea = serviceArea;
        this.hourlyCost = hourlyCost;
        this.geoLocationBoundary = geoLocationBoundary;
        this.overallRating = overallRating;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public String getHourlyCost() {
        return hourlyCost;
    }

    public String getGeoLocationBoundary() {
        return geoLocationBoundary;
    }

    public String getOverallRating() {
        return overallRating;
    }

    public String getStatus() {
        return status;
    }
}
