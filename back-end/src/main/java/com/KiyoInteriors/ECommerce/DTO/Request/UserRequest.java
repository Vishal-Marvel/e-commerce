package com.KiyoInteriors.ECommerce.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UserRequest {
    @NotBlank(message = "Username is necessary")
    private String username;
    @NotBlank(message = "Name is necessary")
    private String name;
    @NotBlank(message = "Password is necessary")
    private String password;
    @NotBlank(message = "Mobile Number is necessary")
    private String mobile;
    private List<String> addresses;
    private MultipartFile photo;
    @NotBlank(message = "Email is necessary")
    private String email;
}
