package org.learnhub.backend.database.repostitory;

import org.learnhub.backend.database.entity.SchoolMember;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SchoolMembersRepository extends CrudRepository<SchoolMember, Long> {

    @Query("SELECT sm FROM SchoolMember sm WHERE sm.account.email = :email AND sm.school.id = :id")
    Optional<SchoolMember> findSchoolMemberByUserEmailAndSchoolId(@Param("email") String email, @Param("id") Long id);

}
