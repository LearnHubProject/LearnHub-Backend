package org.learnhub.backend.data.dto;

import org.learnhub.backend.model.SchoolRole;

public class SchoolMemberDTO {

    Long id;

    SchoolDetailsDTO school;

    SchoolRole role;
    UserDetailsDTO userAccount;

    public SchoolMemberDTO(Long id, SchoolDetailsDTO school, SchoolRole role, UserDetailsDTO userAccount) {
        this.id = id;
        this.school = school;
        this.role = role;
        this.userAccount = userAccount;
    }

    public SchoolDetailsDTO getSchool() {
        return school;
    }

    public Long getId() {
        return id;
    }

    public SchoolRole getRole() {
        return role;
    }

    public UserDetailsDTO getUserAccount() {
        return userAccount;
    }
}
