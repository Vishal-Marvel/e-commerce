package com.KiyoInteriors.ECommerce.controller;

import com.KiyoInteriors.ECommerce.DTO.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.validation.Valid;
import com.KiyoInteriors.ECommerce.service.AuthenticationService;
import com.KiyoInteriors.ECommerce.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/auth" )
public class AuthenticationController
{
    private final UserService userService;
    private final AuthenticationService authService;

    @PostMapping( "/register" )
    public ResponseEntity<MiscResponse> register(@Valid @ModelAttribute UserDTO userDTO) throws IOException {

        authService.register(userDTO);
        return ResponseEntity.ok(MiscResponse.builder().response("User Registered").build());

    }

    @PostMapping( "/authenticate" )
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<MiscResponse> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
        authService.changePassword(changePasswordDTO);
        return ResponseEntity.ok(MiscResponse.builder()
                .response("Password Changed").build());
    }

    @PostMapping({ "/logout" })
    public ResponseEntity<MiscResponse> logout() {
        authService.logout();
        return ResponseEntity.ok(MiscResponse.builder().response("Logged out").build());
    }



}
