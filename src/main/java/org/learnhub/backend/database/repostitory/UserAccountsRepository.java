package org.learnhub.backend.database.repostitory;

import org.learnhub.backend.database.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountsRepository extends CrudRepository<UserAccount, Long> {
    boolean existsByEmail(String email);
    UserAccount findByEmail(String email);
}
