package com.KiyoInteriors.ECommerce.DTO.Request;

import lombok.Data;
/**
 * A class representing a Reset Password request.
 * This class encapsulates the information required to reset a user's password.
 */
@Data
public class ResetPasswordRequest {
    private String email;
}
