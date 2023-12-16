package org.learnhub.backend.model;

public enum AuthenticationStatus {
    SUCCESS("Success"),
    FAILURE("Failure");

    private final String value;

    AuthenticationStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
