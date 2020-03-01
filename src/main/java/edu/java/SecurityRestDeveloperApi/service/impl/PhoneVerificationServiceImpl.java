package edu.java.SecurityRestDeveloperApi.service.impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import edu.java.SecurityRestDeveloperApi.model.Phone;
import edu.java.SecurityRestDeveloperApi.service.PhoneVerificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class PhoneVerificationServiceImpl implements PhoneVerificationService {

    @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.twilio_phone_number}")
    private String twilioPhoneNumber;

    @PostConstruct
    public void twilioInit() {
        Twilio.init(accountSid, authToken);
    }

    @Override
    public void sendVerificationCode(Phone phone) {
        Message.creator(
                new PhoneNumber(phone.getPhoneNumber()),
                new PhoneNumber(twilioPhoneNumber),
                "Verification code: " + phone.getVerificationCode())
                .create();
    }
}
