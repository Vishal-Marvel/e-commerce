package com.KiyoInteriors.ECommerce.DTO.Response;

import lombok.Builder;
import lombok.Data;
/**
 * This class represents an authentication response that contains a token and a role for a user.
 * It uses the @Data and @Builder annotations from Lombok to generate getters, setters, constructors, and builders for the fields.
 */
@Data
@Builder
public class AuthenticationResponse {
    private String token;
    private String role;
}
