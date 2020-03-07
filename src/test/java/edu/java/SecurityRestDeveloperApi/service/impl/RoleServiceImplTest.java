package edu.java.SecurityRestDeveloperApi.service.impl;

import edu.java.SecurityRestDeveloperApi.model.Role;
import edu.java.SecurityRestDeveloperApi.repository.RoleRepository;
import edu.java.SecurityRestDeveloperApi.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RoleServiceImplTest {

    RoleRepository roleRepository = mock(RoleRepository.class);
    RoleService roleService = new RoleServiceImpl(roleRepository);
    Role role = new Role();

    @BeforeEach
    public void setUp() {
        role.setName("RoleName");
    }

    @Test
    void getAll() {
        roleService.getAll();
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    void findByUsername() {
        roleService.findByName("RoleName");
        verify(roleRepository, times(1)).findByName("RoleName");
    }

    @Test
    void findById() {
        roleService.findById(1L);
        verify(roleRepository, times(1)).findById(1L);
    }

    @Test
    void delete() {
        roleService.delete(1L);
        verify(roleRepository, times(1)).deleteById(1L);
    }

    @Test
    void save() {
        roleService.save(role);
        verify(roleRepository, times(1)).save(role);
        verify(roleRepository, times(1)).flush();
    }

    @Test
    void update() {
        roleService.update(role);
        verify(roleRepository, times(1)).save(role);
        verify(roleRepository, times(1)).flush();
    }
}