package edu.java.SecurityRestDeveloperApi.service;

import edu.java.SecurityRestDeveloperApi.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAll();

    Role findByName(String name);

    Role findById(Long id);

    void delete(long id);

    void save(Role role);

    void update(Role role);
}
