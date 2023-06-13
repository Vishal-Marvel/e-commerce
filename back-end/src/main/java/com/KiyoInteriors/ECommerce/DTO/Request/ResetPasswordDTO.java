package com.KiyoInteriors.ECommerce.DTO.Request;

import lombok.Data;
/**
 * A class representing a Reset Password data transfer object (DTO).
 * This class encapsulates the information required to reset a user's password.
 */
@Data
public class ResetPasswordDTO {
    private String newPassword;
}
