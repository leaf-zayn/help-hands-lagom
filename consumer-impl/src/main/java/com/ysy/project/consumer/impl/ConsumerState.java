package com.ysy.project.consumer.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class ConsumerState {
    private final String name;
    private final String address;
    private final String mobile;
    private final String email;
    private final String geoLocation;

    @JsonCreator
    public ConsumerState(String name, String address, String mobile, String email, String geoLocation) {
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
