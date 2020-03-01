package edu.java.SecurityRestDeveloperApi.service;

import edu.java.SecurityRestDeveloperApi.model.Phone;

public interface PhoneVerificationService {

    void sendVerificationCode(Phone phone);
}
