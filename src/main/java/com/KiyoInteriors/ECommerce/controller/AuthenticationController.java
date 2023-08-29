package com.KiyoInteriors.ECommerce.controller;

import com.KiyoInteriors.ECommerce.DTO.Request.*;
import com.KiyoInteriors.ECommerce.DTO.Response.AuthenticationResponse;
import com.KiyoInteriors.ECommerce.DTO.Response.CityDetails;
import com.KiyoInteriors.ECommerce.DTO.Response.MiscResponse;
import com.KiyoInteriors.ECommerce.config.GoogleOAuth2Config;
import com.KiyoInteriors.ECommerce.entity.User;
import com.KiyoInteriors.ECommerce.entity.UserRole;
import com.KiyoInteriors.ECommerce.exceptions.APIException;
import com.KiyoInteriors.ECommerce.exceptions.UserNotFoundException;
import com.KiyoInteriors.ECommerce.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import com.KiyoInteriors.ECommerce.service.AuthenticationService;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

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
    public ResponseEntity<MiscResponse> register(@Valid @RequestBody RegisterRequest registerRequest) throws IOException, MessagingException {
        authService.register(registerRequest);
        return ResponseEntity.ok(MiscResponse.builder().response("User Registered, Verification Mail Sent").build());
    }

    @GetMapping("/city-details/{pinCode}")
    public ResponseEntity<CityDetails> getCityDetails(@PathVariable String pinCode) throws Exception {
        String url = "https://api.postalpincode.in/pincode/" + pinCode;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode postOfficeNode = rootNode.get(0).get("PostOffice");
            List<String> cities = new ArrayList<>();
            String state = null;
            String country = null;
            String district = null;

            for (JsonNode node : postOfficeNode) {
                state = node.get("State").asText();
                country = node.get("Country").asText();
                district = node.get("District").asText();
                String name = node.get("Name").asText();
                cities.add(name);
            }
            return ResponseEntity.ok(CityDetails.builder()
                    .city(cities)
                    .state(state)
                    .country(country)
                    .district(district)
                    .build());

        } else {
            throw new APIException("Request Failed", (HttpStatus) response.getStatusCode());
        }

    }

    @PostMapping( "/authenticate" )
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/verify")
    public ResponseEntity<MiscResponse> verify(
            @RequestParam("code") String code
    ) throws Exception {
        authService.verifyUser(code);
        return ResponseEntity.ok(MiscResponse.builder()
                .response("User Verified").build());
    }

    @PostMapping("/email-verify")
    public ResponseEntity<MiscResponse> emailVerify(
            @RequestParam("code") String code
    )  {
        authService.verifyUserAndChangeMail(code);
        return ResponseEntity.ok(MiscResponse.builder()
                .response("Mail Changed").build());
    }

    @PostMapping("/dynamic-verify")
    public ResponseEntity<MiscResponse> dynamicVerify(@RequestBody ResetPasswordRequest resetPasswordRequest) throws MessagingException {
        authService.requestVerify(resetPasswordRequest);
        return ResponseEntity.ok(MiscResponse.builder()
                .response("Verification Link Sent").build());
    }

    @SecurityRequirement(name = "Bearer Authentication")
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
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping({ "/logout" })
    public ResponseEntity<MiscResponse> logout() {
        authService.logout();
        return ResponseEntity.ok(MiscResponse.builder().response("Logged out").build());
    }

    @PostMapping("/updatePrivilege/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
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

    @PostMapping("/oauth2/callback/google")
    public ResponseEntity<AuthenticationResponse> handleGoogleCallback(@RequestBody RegisterRequest request) throws MessagingException {
        if (!userRepository.existsByEmail(request.getEmail())) {
            authService.oAuth2register(request);

        }
            AuthenticationRequest authenticationRequest = new AuthenticationRequest();
            authenticationRequest.setPassword(request.getPassword());
            authenticationRequest.setEmail(request.getEmail());
            return ResponseEntity.ok(authService.authenticate(authenticationRequest));
     }


}
