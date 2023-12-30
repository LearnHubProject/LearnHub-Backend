package org.learnhub.backend.data.dto;

import org.learnhub.backend.model.SchoolRole;

public class SchoolMemberDTO {

    Long id;

    SchoolRole role;

    UserDetailsDTO userAccount;

    public SchoolMemberDTO(Long id, SchoolRole role, UserDetailsDTO userAccount) {
        this.id = id;
        this.role = role;
        this.userAccount = userAccount;
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
