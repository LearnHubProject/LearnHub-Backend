package org.learnhub.backend.database.repostitory;

import org.learnhub.backend.database.entity.School;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolsRepository extends CrudRepository<School, Long> {

}