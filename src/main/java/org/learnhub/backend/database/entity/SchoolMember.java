package org.learnhub.backend.database.entity;

import jakarta.persistence.*;
import org.learnhub.backend.model.SchoolRole;

import java.util.Set;

@Entity
@Table(name="school_members"
, uniqueConstraints ={ @UniqueConstraint(columnNames = {"school_id","user_account_id"})})
public class SchoolMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    SchoolRole role;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    School school;

    @OneToMany(mappedBy = "schoolMember", cascade = CascadeType.ALL)
    Set<ClassMember> classMembers;

    @ManyToOne
    @JoinColumn(name = "user_account_id", nullable = false)
    UserAccount account;

    public Set<ClassMember> getClassMembers() {
        return classMembers;
    }

    public SchoolMember() {
    }
    

    public void setSchool(School school) {
        this.school = school;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    public void setRole(SchoolRole role) {
        this.role = role;
    }

    public SchoolRole getRole() {
        return role;
    }

    public School getSchool() {
        return school;
    }

    public UserAccount getAccount() {
        return account;
    }
}
