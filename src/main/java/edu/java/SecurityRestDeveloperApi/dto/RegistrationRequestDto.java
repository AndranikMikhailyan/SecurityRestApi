package edu.java.SecurityRestDeveloperApi.dto;

import edu.java.SecurityRestDeveloperApi.model.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class RegistrationRequestDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setStatus(Status.NOT_ACTIVE);
        user.setCreated(new Date());
        user.setUpdated(new Date());
        return user;
    }

    public Phone toPhone() {
        Phone phone = new Phone();
        phone.setPhoneNumber(phoneNumber);
        phone.setStatus(Status.ACTIVE);
        phone.setVerificationStatus(VerificationStatus.NOT_VERIFIED);
        phone.setCreated(new Date());
        phone.setUpdated(new Date());
        return phone;
    }
}
