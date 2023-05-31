package com.KiyoInteriors.ECommerce.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class SizeQuantity {
    private ProductSize size;
    private Integer quantity;

}
