package org.learnhub.backend.data.mapper;

import org.learnhub.backend.data.dto.UserDetailsDTO;
import org.learnhub.backend.database.entity.UserAccount;

public class UserMapper {

    public static UserDetailsDTO UserAccountToUserDetails(UserAccount userAccount){
        return new UserDetailsDTO(userAccount.id, userAccount.email);
    }

}
