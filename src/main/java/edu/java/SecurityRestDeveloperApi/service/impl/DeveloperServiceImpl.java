package edu.java.SecurityRestDeveloperApi.service.impl;

import edu.java.SecurityRestDeveloperApi.model.Developer;
import edu.java.SecurityRestDeveloperApi.repository.DeveloperRepository;
import edu.java.SecurityRestDeveloperApi.service.DeveloperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    @Autowired
    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developers = developerRepository.findAll();
        log.info("In getAll {} developers found", developers.size());
        return developers;
    }

    @Override
    public Developer findById(Long id) {
        Developer developer = developerRepository.findById(id).orElse(null);
        if (developer == null) {
            log.info("In findById developer with id: {} is not found", id);
        } else {
            log.info("In findById developer: {} found by id: {}", developer, id);
        }
        return developer;
    }

    @Override
    public void delete(long id) {
        developerRepository.deleteById(id);
        log.info("In delete developer  deleted by id: {}", id);    }

    @Override
    public Developer save(Developer developer) {
        developer = developerRepository.save(developer);
        developerRepository.flush();
        log.info("In save developer: {} successfully saved", developer);
        return developer;
}

    @Override
    public Developer update(Developer developer) {
        developer.setUpdated(new Date());
        developer = developerRepository.save(developer);
        developerRepository.flush();
        log.info("In update developer: {} successfully updated", developer);
        return developer;
    }
}
