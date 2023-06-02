package com.KiyoInteriors.ECommerce.service;

import com.KiyoInteriors.ECommerce.DTO.Request.ChangePasswordDTO;
import com.KiyoInteriors.ECommerce.entity.Cart;
import com.KiyoInteriors.ECommerce.entity.Image;
import com.KiyoInteriors.ECommerce.exceptions.ConstraintException;
import com.KiyoInteriors.ECommerce.exceptions.UserNotFoundException;
import com.KiyoInteriors.ECommerce.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.KiyoInteriors.ECommerce.DTO.Response.AuthenticationResponse;
import com.KiyoInteriors.ECommerce.DTO.Request.AuthenticationRequest;
import java.io.IOException;
import java.util.Optional;

import com.KiyoInteriors.ECommerce.entity.UserRole;
import com.KiyoInteriors.ECommerce.entity.User;
import com.KiyoInteriors.ECommerce.DTO.Request.UserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.KiyoInteriors.ECommerce.security.JWTTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
/**

The "AuthenticationService" class is responsible for user authentication, registration, password change, and logout operations.

It interacts with the "UserRepository", "AuthenticationManager", "JWTTokenProvider", "PasswordEncoder", and "CartRepository" to perform these operations.

register(UserRequest userDTO): Registers a new user based on the provided user request.
*/
@Service
@RequiredArgsConstructor
public class AuthenticationService
{
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;

    public void register(final UserRequest userDTO) throws IOException {
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
        Image image = Image.builder()
                .data(userDTO.getPhoto().getBytes())
                .fileName(userDTO.getPhoto().getOriginalFilename())
                .build();
        user.setPhoto(image);
        user.setRole(UserRole.ROLE_USER);
        userRepository.save(user);
        Cart cart = new Cart(user);
        cartRepository.save(cart);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest loginDTO) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
        User user = userRepository.findUserByUsernameOrEmail(
                loginDTO.getUsernameOrEmail(), loginDTO.getUsernameOrEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User Not found"));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return AuthenticationResponse.builder()
                .token(jwtTokenProvider
                        .generateToken(auth))
                .role(user.getRole().name())
                .build();
    }

    public void changePassword(ChangePasswordDTO changePasswordDTO){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            username, changePasswordDTO.getOldPassword()
        ));
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(()-> new UserNotFoundException("User Not Found"));
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
