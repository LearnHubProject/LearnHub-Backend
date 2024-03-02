package org.learnhub.backend.data.dto;

public class UserDetailsDTO {

    Long id;

    String email;

    Long personalCode;

    public UserDetailsDTO(Long id, String email, Long personalCode) {
        this.id = id;
        this.email = email;
        this.personalCode = personalCode;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Long getPersonalCode() {
        return personalCode;
    }
}
