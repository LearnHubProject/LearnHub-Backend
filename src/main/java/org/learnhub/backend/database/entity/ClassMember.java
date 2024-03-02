package org.learnhub.backend.database.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "class_members")
public class ClassMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    Class aClass;

    @ManyToOne
    @JoinColumn(name = "school_member_id", nullable = false)
    SchoolMember schoolMember;


    public ClassMember() {
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public void setSchoolMember(SchoolMember schoolMember) {
        this.schoolMember = schoolMember;
    }

    public Long getId() {
        return id;
    }

    public Class getaClass() {
        return aClass;
    }

    public SchoolMember getSchoolMember() {
        return schoolMember;
    }
}
