package org.learnhub.backend.api.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {

    @JsonProperty
    String email;

    @JsonProperty
    String password;

    @JsonProperty("personalCode")
    Long personalCode;

    public RegisterRequest() {
    }

    public RegisterRequest(String email, String password, Long personalCode) {
        this.email = email;
        this.password = password;
        this.personalCode = personalCode;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getPersonalCode() {
        return personalCode;
    }
}
