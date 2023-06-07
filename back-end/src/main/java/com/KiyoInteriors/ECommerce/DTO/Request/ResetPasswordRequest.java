package com.KiyoInteriors.ECommerce.DTO.Request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String usernameOrEmail;
}
