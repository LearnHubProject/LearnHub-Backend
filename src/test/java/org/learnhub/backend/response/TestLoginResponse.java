package org.learnhub.backend.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestLoginResponse {

    @JsonProperty
    String token;

    public TestLoginResponse() {
    }

    public TestLoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
