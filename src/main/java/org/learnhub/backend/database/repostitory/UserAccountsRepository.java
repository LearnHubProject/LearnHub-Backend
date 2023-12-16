package org.learnhub.backend.database.repostitory;

import org.learnhub.backend.database.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountsRepository extends CrudRepository<UserAccount, Long> {
    public boolean existsByEmail(String email);
    public UserAccount findByEmail(String email);
}
