package org.learnhub.backend.api.payload.response;

import org.learnhub.backend.misc.payloads.ResponsePayload;


public class AuthenticationResponse implements ResponsePayload {

    String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
