package com.KiyoInteriors.ECommerce.entity;

import java.util.Date;
import java.util.UUID;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
/**
 * 
 * This class represents a User entity in the system.
 * It contains various properties that define the attributes and characteristics
 * of a user.
 * The properties include id, username, name, password, mobile, addresses,
 * photo, email, and role.
 * The id field is a unique identifier generated using UUID.
 * The username field is used as a unique identifier for the user and must be
 * unique across the system.
 * The email field is also used as a unique identifier for the user and must be
 * unique across the system.
 * The addresses field stores a list of addresses associated with the user.
 * The photo field represents an image associated with the user.
 * The role field represents the role or privileges assigned to the user.
 */
public class User {
    @Id
    private String id = UUID.randomUUID().toString();
    @Indexed(unique = true)
    private String username;
    private String password;
    private String mobile;
    private List< Address> addresses;
    private String photo;
    @Indexed(unique = true)
    private String email;
    private String tempEmail;
    private UserRole role;
    private Date lastLoggedIn;
    private String OTP;
    private Date OTPLimit;
    private boolean verified;

}
