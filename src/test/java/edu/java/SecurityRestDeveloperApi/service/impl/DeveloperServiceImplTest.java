package edu.java.SecurityRestDeveloperApi.service.impl;

import edu.java.SecurityRestDeveloperApi.model.Developer;
import edu.java.SecurityRestDeveloperApi.repository.DeveloperRepository;
import edu.java.SecurityRestDeveloperApi.service.DeveloperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeveloperServiceImplTest {

    DeveloperRepository developerRepository = mock(DeveloperRepository.class);
    DeveloperService developerService = new DeveloperServiceImpl(developerRepository);
    Developer developer = new Developer();

    @BeforeEach
    public void setUp() {
        developer.setFirstName("firstName");
    }

    @Test
    void getAll() {
        developerService.getAll();
        verify(developerRepository, times(1)).findAll();
    }


    @Test
    void findById() {
        developerService.findById(1L);
        verify(developerRepository, times(1)).findById(1L);
    }

    @Test
    void delete() {
        developerService.delete(1L);
        verify(developerRepository, times(1)).deleteById(1L);
    }

    @Test
    void save() {
        developerService.save(developer);
        verify(developerRepository, times(1)).save(developer);
        verify(developerRepository, times(1)).flush();
    }

    @Test
    void update() {
        developerService.update(developer);
        verify(developerRepository, times(1)).save(developer);
        verify(developerRepository, times(1)).flush();
    }
}