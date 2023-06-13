package com.KiyoInteriors.ECommerce.entity;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
/**
 * A class representing a raw image.
 * This class holds the ID and data of a raw image, where the data is stored as a byte array.
 */
@Data
@Document
public class RawImage {
    @Id
    private String id = UUID.randomUUID().toString();
    private byte[] data;
}
