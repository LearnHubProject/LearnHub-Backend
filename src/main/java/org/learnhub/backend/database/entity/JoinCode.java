package org.learnhub.backend.database.entity;

import jakarta.persistence.*;


//TODO: unclear how it will be implemented, but I'll leave it for now here
@Entity
@Table(name = "join_codes")
public class JoinCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true)
    String code;

    @OneToOne
    @MapsId
    School school;

    @Column
    int maxUsages;

    @Column
    Long expirationTime;

}
