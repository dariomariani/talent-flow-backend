package com.dariom.domain.model;

public enum ApplicationStatus {
    PENDING("Waiting for feedback"),
    ACCEPTED("Accepted"),
    REJECTED("Rejected"),;

    public final String displayName;

    ApplicationStatus(String displayName) {
        this.displayName = displayName;
    }

    public static ApplicationStatus fromDisplayName(String displayName) {
        for (ApplicationStatus status : ApplicationStatus.values()) {
            if (status.displayName.equalsIgnoreCase(displayName)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant with displayName: " + displayName);
    }
}
