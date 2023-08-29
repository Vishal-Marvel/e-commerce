package com.KiyoInteriors.ECommerce.config;

import com.twilio.Twilio;
import org.springframework.context.annotation.Configuration;
/**
 * A class responsible for initializing the Twilio API.
 * This class initializes the Twilio API by setting the account SID and authentication token
 * based on the provided Twilio configuration.
 */
//@Configuration
public class TwilioInitializer {
    private final TwilioConfiguration twilioConfiguration;

    public TwilioInitializer(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken());
    }

}
