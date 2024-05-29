package com.ysy.project.order.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.serialization.Jsonable;

@JsonDeserialize
public class OrderState implements Jsonable {
    private final String serviceId;
    private final String consumerId;
    private final String providerId;
    private final String timeSlot;
    private final String cost;
    private final String status;
    private final String rating;

    @JsonCreator
    public OrderState(String serviceId, String consumerId, String providerId, String timeSlot, String cost, String status, String rating) {
        this.serviceId = serviceId;
        this.consumerId = consumerId;
        this.providerId = providerId;
        this.timeSlot = timeSlot;
        this.cost = cost;
        this.status = status;
        this.rating = rating;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public String getCost() {
        return cost;
    }

    public String getStatus() {
        return status;
    }

    public String getRating() {
        return rating;
    }
}
