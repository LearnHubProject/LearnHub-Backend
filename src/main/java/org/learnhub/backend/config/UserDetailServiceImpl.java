package org.learnhub.backend.config;

import org.learnhub.backend.model.UserAccount;
import org.learnhub.backend.model.UserCredentials;
import org.learnhub.backend.repostitory.UserAccountsRepository;
import org.learnhub.backend.repostitory.UserCredentialsRepository;
import org.learnhub.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

public class UserDetailsManagerImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
