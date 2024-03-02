package org.learnhub.backend.database.repostitory;

import org.learnhub.backend.database.entity.Class;
import org.springframework.data.repository.CrudRepository;

public interface ClassesRepository extends CrudRepository<Class, Long> {
}
