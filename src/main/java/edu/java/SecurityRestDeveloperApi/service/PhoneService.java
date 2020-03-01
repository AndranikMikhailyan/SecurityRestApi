package edu.java.SecurityRestDeveloperApi.service;

import edu.java.SecurityRestDeveloperApi.model.Phone;

import java.util.List;

public interface PhoneService {

    List<Phone> getAll();

    Phone findByPhoneNumber(String phoneNumber);

    Phone findById(Long id);

    void delete(long id);

    void save(Phone phone);

    void update(Phone phone);
}
