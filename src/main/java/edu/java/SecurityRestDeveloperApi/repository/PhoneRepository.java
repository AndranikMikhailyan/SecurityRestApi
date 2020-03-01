package edu.java.SecurityRestDeveloperApi.repository;

import edu.java.SecurityRestDeveloperApi.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Phone findByPhoneNumber(String number);
}
