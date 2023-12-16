package org.learnhub.backend.database.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "schools")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String name;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<SchoolMember> members;

    public School() {
    }

    public School(String name) {
        this.name = name;
        this.members = new HashSet<SchoolMember>();
    }

    public void addMember(SchoolMember schoolMember){
        schoolMember.setSchool(this);
        members.add(schoolMember);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<SchoolMember> getMembers() {
        return members;
    }
}
