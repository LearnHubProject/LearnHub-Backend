package org.learnhub.backend.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestLoginRequest {

    @JsonProperty
    String email;

    @JsonProperty
    String password;

    public TestLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
