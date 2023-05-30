package com.KiyoInteriors.ECommerce.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordDTO {
    @NotBlank(message = "Old Password is necessary")
    private String oldPassword;
    @NotBlank(message = "New Password is necessary")
    private String newPassword;
}
