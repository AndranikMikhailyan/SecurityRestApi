package edu.java.SecurityRestDeveloperApi.repository;

import edu.java.SecurityRestDeveloperApi.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
