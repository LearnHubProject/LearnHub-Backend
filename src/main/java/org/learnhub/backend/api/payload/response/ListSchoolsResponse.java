package org.learnhub.backend.api.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.learnhub.backend.misc.payloads.ResponsePayload;

import java.util.List;

public class ListSchoolsResponse implements ResponsePayload {

    @JsonProperty("schools")
    List<School> schools;

    public static class School{

        @JsonProperty("id")
        Long id;
        @JsonProperty("name")
        String name;

        public School(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public ListSchoolsResponse() {
    }

    public ListSchoolsResponse(List<School> schools) {
        this.schools = schools;
    }
}
