package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.entity.User;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.List;

/**
 * 
 * The "CustomUserDetailsService" class is an implementation of the Spring
 * Security "UserDetailsService" interface.
 * It is responsible for loading user details from the database based on the
 * provided username or email during the authentication process.
 * loadUserByUsername(String usernameOrEmail): Loads user details from the
 * database based on the provided username or email.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        if(!user.isActive()){

            try {
                throw new AccessDeniedException("User is Not Verified");
            } catch (AccessDeniedException e) {
                throw new RuntimeException(e);
            }

        }
        List<GrantedAuthority> authorities = Collections
                .singletonList(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                authorities);
    }

}
