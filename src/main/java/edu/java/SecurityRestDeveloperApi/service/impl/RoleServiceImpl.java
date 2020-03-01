package edu.java.SecurityRestDeveloperApi.service.impl;

import edu.java.SecurityRestDeveloperApi.model.Role;
import edu.java.SecurityRestDeveloperApi.model.User;
import edu.java.SecurityRestDeveloperApi.repository.RoleRepository;
import edu.java.SecurityRestDeveloperApi.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = roleRepository.findAll();
        log.info("In getAll - {} roles found", roles.size());
        return roles;
    }

    @Override
    public Role findByName(String name) {
        Role role = roleRepository.findByName(name);
        log.info("In findByName - Role: {} found by name: {}", role, name);
        return role;
    }

    @Override
    public Role findById(Long id) {
        Role role = roleRepository.findById(id).orElse(null);

        if (role == null) {
            log.warn("In findById no role found by id: {}", id);
        }

        log.info("In findById - role: {} found by id: {}", role, id);
        return role;
    }

    @Override
    public void delete(long id) {
        roleRepository.deleteById(id);
        log.info("In delete - role with id: {} successfully deleted", id);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
        roleRepository.flush();
        log.info("In save - role: {} successfully saved", role);
    }

    @Override
    public void update(Role role) {
        role.setUpdated(new Date());
        roleRepository.save(role);
        roleRepository.flush();
        log.info("In update - role: {} successfully updated", role);
    }
}
