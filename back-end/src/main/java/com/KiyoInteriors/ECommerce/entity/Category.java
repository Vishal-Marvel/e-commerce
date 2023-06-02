package com.KiyoInteriors.ECommerce.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
/**
 * This class represents a category entity that has an id and a name.
 * The id is a randomly generated UUID string that uniquely identifies the category.
 * The name is a string that describes the category.
 */
@Data
@Document
public class Category {
    @Id
    private String id = UUID.randomUUID().toString();
    private String category;

}
