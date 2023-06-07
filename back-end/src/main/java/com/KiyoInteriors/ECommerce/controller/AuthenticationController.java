package com.KiyoInteriors.ECommerce.controller;

import com.KiyoInteriors.ECommerce.DTO.Request.*;
import com.KiyoInteriors.ECommerce.DTO.Response.AuthenticationResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.MiscResponse;
import com.KiyoInteriors.ECommerce.entity.User;
import com.KiyoInteriors.ECommerce.entity.UserRole;
import com.KiyoInteriors.ECommerce.exceptions.UserNotFoundException;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import com.KiyoInteriors.ECommerce.service.AuthenticationService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/auth" )
/**
 * This class provides methods for user authentication and authorization.
 * It uses the UserRepository and AuthenticationService classes to perform
 * various operations related to user accounts and privileges.
 */
public class AuthenticationController
{
    private final UserRepository userRepository;
    private final AuthenticationService authService;

    @PostMapping( "/register" )
    public ResponseEntity<MiscResponse> register(@Valid @ModelAttribute UserRequest userDTO) throws IOException, MessagingException {
        authService.register(userDTO);
        return ResponseEntity.ok(MiscResponse.builder().response("User Registered").build());

    }

    @PostMapping( "/authenticate" )
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/verify")
    public ResponseEntity<MiscResponse> verify(
            @RequestParam("code") String code
    ){
        authService.verifyUser(code);
        return ResponseEntity.ok(MiscResponse.builder()
                .response("User Verified").build());
    }

    @PostMapping("/change-password")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<MiscResponse> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO){
        authService.changePassword(changePasswordDTO);
        return ResponseEntity.ok(MiscResponse.builder()
                .response("Password Changed").build());
    }

    @PostMapping("/reset-password")
    public ResponseEntity<MiscResponse> resetPassword(@RequestBody ResetPasswordDTO resetPasswordRequest,
                                                      @RequestParam("code") String code){
        authService.resetPassword(code, resetPasswordRequest);
        return ResponseEntity.ok(MiscResponse.builder()
                .response("Password Changed").build());
    }

    @PostMapping("/reset-password-request")
    public ResponseEntity<MiscResponse> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) throws MessagingException {
        authService.requestResetPassword(resetPasswordRequest);
        return ResponseEntity.ok(MiscResponse.builder()
                .response("Reset Link Sent").build());
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping({ "/logout" })
    public ResponseEntity<MiscResponse> logout() {
        authService.logout();
        return ResponseEntity.ok(MiscResponse.builder().response("Logged out").build());
    }

    @PostMapping("/updatePrivilege/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MiscResponse> changePrivilege(@PathVariable String id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        user.setRole(UserRole.ROLE_ADMIN);
        userRepository.save(user);
        return ResponseEntity.ok(MiscResponse
                .builder()
                .response("Privileges Updated")
                .build());
    }

}
