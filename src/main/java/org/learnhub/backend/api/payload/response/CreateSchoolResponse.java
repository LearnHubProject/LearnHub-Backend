package org.learnhub.backend.api.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.learnhub.backend.misc.payloads.ResponsePayload;

public class CreateSchoolResponse implements ResponsePayload {

    @JsonProperty("id")
    Long schoolId;

    @JsonProperty("name")
    String schoolName;

    public CreateSchoolResponse(Long schoolId, String schoolName) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public String getSchoolName(){return schoolName;}

}
