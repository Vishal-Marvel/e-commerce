package com.KiyoInteriors.ECommerce.DTO.Request;
/**
* A class representing an SMS request.
* This class encapsulates the information required to send an SMS message.
* The phone number of the recipient.
* The content of the SMS message.
* Constructs a new SmsRequest object with the provided phone number and message.
*
* @param phoneNumber The phone number of the recipient.
* @param message     The content of the SMS message.
 */



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
