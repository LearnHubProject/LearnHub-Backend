package org.learnhub.backend.database.repostitory;

import org.learnhub.backend.database.entity.JoinRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JoinRequestRepository extends CrudRepository<JoinRequest, Long> {

    @Query("SELECT jr FROM JoinRequest jr WHERE jr.school.id = :id")
    List<JoinRequest> listJoinRequestsInSchool(@Param("id") Long schoolId);
}