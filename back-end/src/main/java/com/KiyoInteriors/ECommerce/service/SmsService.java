package com.KiyoInteriors.ECommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KiyoInteriors.ECommerce.DTO.Request.SmsRequest;
import com.KiyoInteriors.ECommerce.config.TwilioConfiguration;
import com.KiyoInteriors.ECommerce.exceptions.ItemNotFoundException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {
    private final TwilioConfiguration twilioConfiguration;

    public SmsService(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    public void sendSms(SmsRequest smsRequest) {
        if (phoneNumberIsValid(smsRequest.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            String message = smsRequest.getMessage();
            Message.creator(to, from, message).create();
        } else {
            throw new ItemNotFoundException("Given Phone number is invalid");
        }
    }

    private boolean phoneNumberIsValid(String phoneNumber) {
        return true;
    }
}
