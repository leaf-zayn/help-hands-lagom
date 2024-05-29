package com.ysy.project.order.api;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

@JsonDeserialize
public class OrderInfo{
    public final String orderId;
    public final String serviceId;
    public final String consumerId;
    public final String providerId;
    public final String timeSlot;
    public final String cost;
    public final String status;
    public final String rating;

    @JsonCreator
    public OrderInfo(String orderId, String serviceId, String consumerId, String providerId, String timeSlot, String cost, String status, String rating) {
        this.orderId = orderId;
        this.serviceId = serviceId;
        this.consumerId = consumerId;
        this.providerId = providerId;
        this.timeSlot = timeSlot;
        this.cost = cost;
        this.status = status;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderInfo orderInfo = (OrderInfo) o;
        return Objects.equals(orderId, orderInfo.orderId) &&
                Objects.equals(serviceId, orderInfo.serviceId) &&
                Objects.equals(consumerId, orderInfo.consumerId) &&
                Objects.equals(providerId, orderInfo.providerId) &&
                Objects.equals(timeSlot, orderInfo.timeSlot) &&
                Objects.equals(cost, orderInfo.cost) &&
                Objects.equals(status, orderInfo.status) &&
                Objects.equals(rating, orderInfo.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, serviceId, consumerId, providerId, timeSlot, cost, status, rating);
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderId='" + orderId + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", consumerId='" + consumerId + '\'' +
                ", providerId='" + providerId + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                ", cost='" + cost + '\'' +
                ", status='" + status + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
