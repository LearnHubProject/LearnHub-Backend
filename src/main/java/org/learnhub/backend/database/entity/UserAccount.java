package org.learnhub.backend.database.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user_accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true)
    String email;

    @Column(name = "personal_code",nullable = false)
    Long personalCode;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    Set<SchoolMember> members;
    //more details to come

    protected UserAccount(){}
    public UserAccount(String email, Long personalCode) {
        this.email = email;
        this.personalCode = personalCode;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Set<SchoolMember> getMembers() {
        return members;
    }

    public Long getPersonalCode() {
        return personalCode;
    }
}
