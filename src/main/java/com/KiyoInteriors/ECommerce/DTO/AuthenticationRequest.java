package com.KiyoInteriors.ECommerce.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class AuthenticationRequest {
    @NotBlank
    private String usernameOrEmail;
    @NotBlank
    private String password;
}
