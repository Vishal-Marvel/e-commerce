package com.KiyoInteriors.ECommerce.entity;

import lombok.Data;

@Data
public class Address {
    private String addressType;
    private String addressLine1;
    private String addressLine2;
    private String pinCode;
    private String state;
    private String district;
    private String city;
    private String country;

}
