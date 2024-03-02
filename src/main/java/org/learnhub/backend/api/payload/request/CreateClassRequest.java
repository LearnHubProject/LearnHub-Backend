package org.learnhub.backend.api.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class CreateClassRequest {


    @JsonProperty("name")
    String className;

    @JsonProperty("teacherID")
    Optional<Long> teacherId;

    public CreateClassRequest(String className, Optional<Long> teacherId) {
        this.className = className;
        this.teacherId = teacherId;
    }



    public String getClassName() {
        return className;
    }

    public Optional<Long> getTeacherId() {
        return teacherId;
    }
}
