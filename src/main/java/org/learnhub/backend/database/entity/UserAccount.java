package org.learnhub.backend.database.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user_accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(unique = true)
    public String email;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    Set<SchoolMember> members;
    //more details to come

    protected UserAccount(){}
    public UserAccount(String email) {
        this.email = email;
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
}
