package com.KiyoInteriors.ECommerce.DTO.Request;

import lombok.Data;
/**
 * A class representing a Change Mail request.
 * This class encapsulates the information required to change a user's email address.
 */
@Data
public class ChangeMailRequest {
    private String newMail;
}
