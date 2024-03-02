package org.learnhub.backend.data.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClassDetailsDTO {

    Long id;

    SchoolDetailsDTO schoolDetails;

    Optional<SchoolMemberDTO> teacher;

    List<ClassMemberDTO> classMembers = new ArrayList<>();

    public ClassDetailsDTO(Long id, SchoolDetailsDTO schoolDetails, SchoolMemberDTO teacher, List<ClassMemberDTO> classMembers) {
        this.id = id;
        this.schoolDetails = schoolDetails;
        this.teacher = Optional.ofNullable(teacher);
        this.classMembers = classMembers;
    }

    public Long getId() {
        return id;
    }

    public SchoolDetailsDTO getSchoolDetails() {
        return schoolDetails;
    }

    public Optional<SchoolMemberDTO> getTeacher() {
        return teacher;
    }

    public List<ClassMemberDTO> getClassMembers() {
        return classMembers;
    }
}
