package com.KiyoInteriors.ECommerce.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
/**
 * This class represents a user request that contains the user's information and photo.
 * It has the following fields:
 * - username: a non-empty string that is the user's username
 * - name: a non-empty string that is the user's name
 * - password: a non-empty string that is the user's password
 * - mobile: a non-empty string that is the user's mobile number
 * - addresses: a list of strings that are the user's addresses
 * - photo: a MultipartFile that is the user's photo
 * - email: a non-empty string that is the user's email address
 */
@Data
public class RegisterRequest {
    @NotBlank(message = "Username is necessary")
    private String username;
    @NotBlank(message = "Password is necessary")
    private String password;
    @NotBlank(message = "Email is necessary")
    private String email;
}
