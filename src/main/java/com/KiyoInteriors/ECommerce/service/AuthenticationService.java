package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.exceptions.ConstraintException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.KiyoInteriors.ECommerce.DTO.AuthenticationResponse;
import com.KiyoInteriors.ECommerce.DTO.AuthenticationRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.KiyoInteriors.ECommerce.entity.UserRole;
import com.KiyoInteriors.ECommerce.entity.User;
import com.KiyoInteriors.ECommerce.DTO.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.KiyoInteriors.ECommerce.security.JWTTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService
{
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public void register(final UserDTO userDTO) throws IOException {
        Optional<User> optionalUser = userRepository.findUserByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail());
        if (optionalUser.isPresent()){
            throw new ConstraintException("User Name or Email Already Exists");
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setMobile(userDTO.getMobile());
        user.setAddresses(userDTO.getAddresses());
        user.setPhoto(userDTO.getPhoto().getBytes());
        user.setRole(UserRole.ROLE_USER);
        userRepository.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest loginDTO) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
        User user = userRepository.findUserByUsernameOrEmail(
                loginDTO.getUsernameOrEmail(), loginDTO.getUsernameOrEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User Not found"));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return AuthenticationResponse.builder().token(jwtTokenProvider.generateToken(auth)).role(user.getRole().name()).build();
    }

    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "Logout";
    }

}