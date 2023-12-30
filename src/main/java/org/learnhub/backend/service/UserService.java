package org.learnhub.backend.service;

import jakarta.transaction.Transactional;
import org.learnhub.backend.data.dto.UserDetailsDTO;
import org.learnhub.backend.data.mapper.UserMapper;
import org.learnhub.backend.database.entity.UserAccount;
import org.learnhub.backend.database.entity.UserCredentials;
import org.learnhub.backend.database.repostitory.UserAccountsRepository;
import org.learnhub.backend.database.repostitory.UserCredentialsRepository;
import org.learnhub.backend.exceptions.UserAlreadyExistAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserAccountsRepository userAccountsRepository;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    PasswordEncoder encoder;

    @Transactional
    public UserAccount createUser(String email, String password, String role) throws UserAlreadyExistAuthenticationException{

        if (userAccountsRepository.existsByEmail(email)){
            throw new UserAlreadyExistAuthenticationException("User with this email already exists.");
        }

        UserAccount userAccount = new UserAccount(email);
        userAccountsRepository.save(userAccount);
        UserCredentials userCredentials = new UserCredentials(userAccount, encoder.encode(password), role);
        userCredentialsRepository.save(userCredentials);
        return  userAccount;

    }

    public UserCredentials getUserCredentialsByEmail(String email){
        UserAccount userAccount = userAccountsRepository.findByEmail(email);
        return userCredentialsRepository.findById(userAccount.id).get();
    }

    public UserDetailsDTO getUserAccountByEmail(String email){
        return UserMapper.UserAccountToUserDetails(userAccountsRepository.findByEmail(email));
    }

    public UserDetailsDTO getUserAccountById(Long id){
        return UserMapper.UserAccountToUserDetails(userAccountsRepository.findById(id).get());
    }
}
