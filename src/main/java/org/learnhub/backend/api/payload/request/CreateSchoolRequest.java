package org.learnhub.backend.api.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateSchoolRequest {

    @JsonProperty("name")
    String name;

    public String getName() {
        return name;
    }

    public CreateSchoolRequest(){}
    public CreateSchoolRequest(String name) {
        this.name = name;
    }
}
