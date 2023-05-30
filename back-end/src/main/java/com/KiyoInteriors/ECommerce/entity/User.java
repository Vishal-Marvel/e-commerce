package com.KiyoInteriors.ECommerce.entity;

import java.util.UUID;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_table")
public class User {
    @Id
    private String id = UUID.randomUUID().toString();
    @Indexed(unique = true)
    private String username;
    private String name;
    private String password;
    private String mobile;
    private List<String> addresses;
    private Image photo ;
    @Indexed(unique = true)
    private String email;
    private UserRole role;

}