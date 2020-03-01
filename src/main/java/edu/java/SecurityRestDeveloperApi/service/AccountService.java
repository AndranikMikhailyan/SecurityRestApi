package edu.java.SecurityRestDeveloperApi.service;

import edu.java.SecurityRestDeveloperApi.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAll();

    Account findById(Long id);

    void delete(long id);

    Account save(Account account);

    Account update(Account account);
}
