package edu.java.SecurityRestDeveloperApi.service.impl;

import edu.java.SecurityRestDeveloperApi.model.Phone;
import edu.java.SecurityRestDeveloperApi.repository.PhoneRepository;
import edu.java.SecurityRestDeveloperApi.service.PhoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PhoneServiceImplTest {

    PhoneRepository phoneRepository = mock(PhoneRepository.class);
    PhoneService phoneService = new PhoneServiceImpl(phoneRepository);
    Phone phone = new Phone();

    @BeforeEach
    public void setUp() {
        phone.setPhoneNumber("+79998885566");
    }

    @Test
    void getAll() {
        phoneService.getAll();
        verify(phoneRepository, times(1)).findAll();
    }

    @Test
    void findByUsername() {
        phoneService.findByPhoneNumber("+79998885566");
        verify(phoneRepository, times(1)).findByPhoneNumber("+79998885566");
    }

    @Test
    void findById() {
        phoneService.findById(1L);
        verify(phoneRepository, times(1)).findById(1L);
    }

    @Test
    void delete() {
        phoneService.delete(1L);
        verify(phoneRepository, times(1)).deleteById(1L);
    }

    @Test
    void save() {
        phoneService.save(phone);
        verify(phoneRepository, times(1)).save(phone);
        verify(phoneRepository, times(1)).flush();
    }

    @Test
    void update() {
        phoneService.update(phone);
        verify(phoneRepository, times(1)).save(phone);
        verify(phoneRepository, times(1)).flush();
    }

}