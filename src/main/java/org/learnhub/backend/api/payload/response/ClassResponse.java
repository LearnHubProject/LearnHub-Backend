package org.learnhub.backend.api.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.learnhub.backend.misc.payloads.ResponsePayload;

import java.util.List;
import java.util.Optional;

public class ClassResponse implements ResponsePayload {

    @JsonProperty("id")
    Long id;

    @JsonProperty("schoolID")
    Long schoolId;

    @JsonProperty("teacherID")
    Optional<Long> teacherID;

    @JsonProperty("studentsIDs")
    List<Long> studentIDs;

    public ClassResponse(Long id, Long schoolId, Optional<Long> teacherID, List<Long> studentIDs) {
        this.id = id;
        this.schoolId = schoolId;
        this.teacherID = teacherID;
        this.studentIDs = studentIDs;
    }
}
