package org.learnhub.backend.data.dto;

import org.learnhub.backend.database.entity.SchoolMember;

public class ClassMemberDTO {

    Long id;
    SchoolMember schoolMember;

    public ClassMemberDTO(Long id, SchoolMember schoolMember) {
        this.id = id;
        this.schoolMember = schoolMember;
    }

    public Long getId() {
        return id;
    }

    public SchoolMember getSchoolMember() {
        return schoolMember;
    }
}
