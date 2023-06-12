package com.KiyoInteriors.ECommerce.DTO.Request;

public class SmsRequest {
    private final String phoneNumber;
    private final String message;

    public SmsRequest(String phoneNumber, String message) {
        this.message = message;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SmsRequest [phoneNumber=" + phoneNumber + ", message=" + message + "]";
    }

}
