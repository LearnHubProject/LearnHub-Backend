package org.learnhub.backend.data.dto;

public class SchoolDetailsDTO {

    Long id;
    String name;

    public SchoolDetailsDTO(Long id, String name) {
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
