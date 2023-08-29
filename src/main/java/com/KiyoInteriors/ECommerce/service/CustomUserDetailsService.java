package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.entity.User;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        if (!user.isVerified()) {
            throw new AccessDeniedException("User is Not Verified");

        }
        List<GrantedAuthority> authorities = Collections
                .singletonList(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                authorities);
    }

}
