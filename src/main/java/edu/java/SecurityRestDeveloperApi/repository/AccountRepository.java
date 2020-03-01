package edu.java.SecurityRestDeveloperApi.repository;

import edu.java.SecurityRestDeveloperApi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
