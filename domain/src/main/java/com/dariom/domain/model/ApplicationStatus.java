package com.dariom.domain.model;

public enum ApplicationStatus {
    PENDING("Waiting for feedback"),
    ACCEPTED("Accepted"),
    REJECTED("Rejected"),;

    public final String displayName;

    ApplicationStatus(String displayName) {
        this.displayName = displayName;
    }
}
