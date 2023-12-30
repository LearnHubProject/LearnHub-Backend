package org.learnhub.backend.api.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.learnhub.backend.data.dto.SchoolDetailsDTO;
import org.learnhub.backend.misc.payloads.ResponsePayload;

import java.util.List;

public class ListSchoolsResponse implements ResponsePayload {

    @JsonProperty
    List<SchoolDetailsDTO> schools;

    public ListSchoolsResponse() {
    }

    public ListSchoolsResponse(List<SchoolDetailsDTO> schools) {
        this.schools = schools;
    }
}
