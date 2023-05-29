package com.KiyoInteriors.ECommerce.controller;

import com.KiyoInteriors.ECommerce.exceptions.UserNotFoundException;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.validation.Valid;
import com.KiyoInteriors.ECommerce.DTO.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import com.KiyoInteriors.ECommerce.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import com.KiyoInteriors.ECommerce.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping({ "/user" })
public class UserController
{
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<User> getUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findUserByUsernameOrEmail(name, name);
        if (user.isEmpty()){
            throw new UserNotFoundException("User Not Exists");
        }
        return ResponseEntity.ok(user.get());
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@Valid @ModelAttribute UserDTO userDTO, Authentication auth) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findUserByUsernameOrEmail(name, name);
        if (user.isEmpty()){
            throw new UserNotFoundException("User Not Exists");
        }
        try {
            this.userService.updateUser(userDTO, user.get().getId());
            return ResponseEntity.ok("User Updated");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}