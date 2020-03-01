package edu.java.SecurityRestDeveloperApi.service;

import edu.java.SecurityRestDeveloperApi.model.Developer;

import java.util.List;

public interface DeveloperService {

    List<Developer> getAll();

    Developer findById(Long id);

    void delete(long id);

    Developer save(Developer developer);

    Developer update(Developer developer);
}
