package com.KiyoInteriors.ECommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KiyoInteriors.ECommerce.DTO.Request.SmsRequest;

@Service
public class SmsSenderService {
    private final SmsService smsService;

    @Autowired
    public SmsSenderService(SmsService smsService) {
        this.smsService = smsService;
    }

    public void sendSms(SmsRequest smsRequest) {
        smsService.sendSms(smsRequest);
    }
}
