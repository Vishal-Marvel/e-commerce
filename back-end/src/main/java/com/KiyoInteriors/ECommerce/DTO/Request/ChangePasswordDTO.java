package com.KiyoInteriors.ECommerce.DTO.Request;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    private String oldPassword;
    private String newPassword;
}
