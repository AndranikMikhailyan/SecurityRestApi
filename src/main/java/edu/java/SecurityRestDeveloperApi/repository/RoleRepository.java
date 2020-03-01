package edu.java.SecurityRestDeveloperApi.repository;

import edu.java.SecurityRestDeveloperApi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
