package com.KiyoInteriors.ECommerce.entity;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document
public class RawImage {
    @Id
    private String id = UUID.randomUUID().toString();
    private byte[] data;
}
