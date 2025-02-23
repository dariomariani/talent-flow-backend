package com.dariom.domain.model;

public enum Role {
    CANDIDATE("Candidate"),
    RECRUITER("Recruiter"),
    MANAGER("Manager");

    public final String name;

    Role(String name) {
        this.name = name;
    }
}
