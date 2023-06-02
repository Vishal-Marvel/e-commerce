package com.KiyoInteriors.ECommerce.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
/**
 * A class that represents an authentication request with a username or email and a password.
 * The fields are annotated with @NotBlank to ensure that they are not null or empty.
 */
@Data

public class AuthenticationRequest {
    @NotBlank
    private String usernameOrEmail;
    @NotBlank
    private String password;
}
