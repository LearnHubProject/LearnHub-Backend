package org.learnhub.backend.database.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "classes")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String label;

    @OneToOne
    SchoolMember teacher;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "aClass", cascade = CascadeType.ALL)
    Set<ClassMember> students;


    public Class() {
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setTeacher(SchoolMember teacher) {
        this.teacher = teacher;
    }

    public void setStudents(Set<ClassMember> students) {
        this.students = students;
    }

    public String getLabel() {
        return label;
    }

    public SchoolMember getTeacher() {
        return teacher;
    }

    public Set<ClassMember> getStudents() {
        return students;
    }
}
