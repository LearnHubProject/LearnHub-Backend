package org.learnhub.backend.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;


@Entity
public class UserCredentials {


    @Id
    private Long id;

    @OneToOne
    @MapsId
    private UserAccount user;
    private String password;
    private String role;

    protected UserCredentials(){}
    public UserCredentials(UserAccount user, String password, String role) {
        this.user = user;
        this.password = password;
        this.role = role;
    }

    public UserAccount getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}

