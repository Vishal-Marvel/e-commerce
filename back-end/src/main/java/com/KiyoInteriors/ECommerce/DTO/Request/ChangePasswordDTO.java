package com.KiyoInteriors.ECommerce.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
/**
 * This class represents a data transfer object for changing a user's password.
 * It has two fields: oldPassword and newPassword, which are both required and cannot be blank.
 * The class is annotated with @Data to generate getters, setters, equals, hashCode and toString methods.
 */
@Data
public class ChangePasswordDTO {
    @NotBlank(message = "Old Password is necessary")
    private String oldPassword;
    @NotBlank(message = "New Password is necessary")
    private String newPassword;
}
