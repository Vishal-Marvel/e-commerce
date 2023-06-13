package com.KiyoInteriors.ECommerce.DTO.Response;

import jakarta.validation.constraints.DecimalMax;
import lombok.Builder;
import lombok.Data;

import java.util.List;
/**
 * A class representing City Details.
 * This class encapsulates the information about a city, including its name, state, country, and district.
 */
@Data
@Builder
public class CityDetails {
    private List<String> city;
    private String state;
    private String country;
    private String district;
}
