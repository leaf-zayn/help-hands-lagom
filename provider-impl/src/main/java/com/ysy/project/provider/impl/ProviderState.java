package com.ysy.project.provider.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class ProviderState {
    private final String name;
    private final String mobile;
    private final String activeSince;
    private final String overallRating;

    @JsonCreator
    public ProviderState(String name, String mobile, String activeSince, String overallRating) {
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
