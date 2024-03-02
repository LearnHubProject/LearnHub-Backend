package org.learnhub.backend.api.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListJoinRequestsPayload {

    @JsonProperty("school")
    Long schoolId;

    public ListJoinRequestsPayload() {
    }

    public Long getSchoolId() {
        return schoolId;
    }
}
