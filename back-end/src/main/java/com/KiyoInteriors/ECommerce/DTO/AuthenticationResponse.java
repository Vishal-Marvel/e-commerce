package com.KiyoInteriors.ECommerce.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private String token;
    private String role;
}
