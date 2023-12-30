package org.learnhub.backend.data.dto;

public class UserDetailsDTO {

    Long id;

    String email;

    public UserDetailsDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
