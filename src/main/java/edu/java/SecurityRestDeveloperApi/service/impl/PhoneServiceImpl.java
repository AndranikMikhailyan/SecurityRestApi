package edu.java.SecurityRestDeveloperApi.service.impl;

import edu.java.SecurityRestDeveloperApi.model.Phone;
import edu.java.SecurityRestDeveloperApi.repository.PhoneRepository;
import edu.java.SecurityRestDeveloperApi.service.PhoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public List<Phone> getAll() {
        List<Phone> phones = phoneRepository.findAll();
        log.info("In getAll -  {} phones found", phones.size());
        return phones;
    }

    @Override
    public Phone findByPhoneNumber(String phoneNumber) {
        Phone phone = phoneRepository.findByPhoneNumber(phoneNumber);
        log.info("In findByPhoneNumber - phone: {} found by phoneNumber: {}", phone, phoneNumber);
        return phone;
    }

    @Override
    public Phone findById(Long id) {
        Phone phone = phoneRepository.findById(id).orElse(null);

        if (phone == null) {
            log.warn("In findById no phone found by id: {}", id);
        }

        log.info("In findById - phone: {} found by id: {}", phone, id);
        return phone;
    }

    @Override
    public void delete(long id) {
        phoneRepository.deleteById(id);
        log.info("In delete - phone with id: {} successfully deleted", id);
    }

    @Override
    public void save(Phone phone) {
        phoneRepository.save(phone);
        phoneRepository.flush();
        log.info("In save - phone: {} successfully saved", phone);
    }

    @Override
    public void update(Phone phone) {
        phone.setUpdated(new Date());
        phoneRepository.save(phone);
        phoneRepository.flush();
        log.info("In update - phone: {} successfully updated", phone);
    }
}
