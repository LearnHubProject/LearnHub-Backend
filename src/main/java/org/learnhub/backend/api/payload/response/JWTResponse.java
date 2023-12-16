package org.learnhub.backend.api.payload.response;


import com.fasterxml.jackson.annotation.JsonProperty;

public class JWTResponse {

    @JsonProperty("jwt")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
