package com.KiyoInteriors.ECommerce.DTO.Response;

import jakarta.validation.constraints.DecimalMax;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CityDetails {
    private List<String> city;
    private String state;
    private String country;
    private String district;
}
