package com.KiyoInteriors.ECommerce.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.KiyoInteriors.ECommerce.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

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
        List<GrantedAuthority> authorities = Collections
                .singletonList(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                authorities);
    }

}
