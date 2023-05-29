package com.KiyoInteriors.ECommerce.controller;

import com.KiyoInteriors.ECommerce.DTO.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import com.KiyoInteriors.ECommerce.DTO.AuthenticationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.validation.Valid;
import com.KiyoInteriors.ECommerce.DTO.UserDTO;
import com.KiyoInteriors.ECommerce.service.AuthenticationService;
import com.KiyoInteriors.ECommerce.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/auth" )
public class AuthenticationController
{
    private final UserService userService;
    private final AuthenticationService authService;

    @PostMapping( "/register" )
    public ResponseEntity<String> register(@Valid @ModelAttribute UserDTO userDTO) {
        try {
            this.authService.register(userDTO);
            return ResponseEntity.ok("User Saved");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping( "/authenticate" )
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping({ "/logout" })
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok(authService.logout());
    }

}
