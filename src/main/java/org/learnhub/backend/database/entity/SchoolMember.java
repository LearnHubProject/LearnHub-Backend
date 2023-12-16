package org.learnhub.backend.database.entity;

import jakarta.persistence.*;

@Entity
@Table(name="school_members")
public class SchoolMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String something;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    School school;

    @ManyToOne
    @JoinColumn(name = "user_account_id", nullable = false)
    UserAccount account;

    public SchoolMember() {
    }

    public SchoolMember(String something) {
        this.something = something;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }
}
