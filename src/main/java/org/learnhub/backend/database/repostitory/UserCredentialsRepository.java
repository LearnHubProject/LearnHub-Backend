package org.learnhub.backend.database.repostitory;

import org.learnhub.backend.database.entity.UserCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends CrudRepository<UserCredentials, Long> {
}
