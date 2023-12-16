package org.learnhub.backend.database.repostitory;

import org.learnhub.backend.database.entity.SchoolMember;
import org.springframework.data.repository.CrudRepository;

public interface SchoolMembersRepository extends CrudRepository<SchoolMember, Long> {
}
