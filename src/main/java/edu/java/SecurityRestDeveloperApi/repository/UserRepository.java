package edu.java.SecurityRestDeveloperApi.repository;

import edu.java.SecurityRestDeveloperApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
