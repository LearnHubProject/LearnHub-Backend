package org.learnhub.backend.api.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetClassTeacherRequest {

    @JsonProperty("teacherID")
    Long teacherId;


    public SetClassTeacherRequest(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

}
