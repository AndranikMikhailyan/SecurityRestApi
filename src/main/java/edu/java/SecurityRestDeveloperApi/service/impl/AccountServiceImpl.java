package edu.java.SecurityRestDeveloperApi.service.impl;

import edu.java.SecurityRestDeveloperApi.model.Account;
import edu.java.SecurityRestDeveloperApi.repository.AccountRepository;
import edu.java.SecurityRestDeveloperApi.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = accountRepository.findAll();
        log.info("In getAll {} accounts found", accounts.size());
        return accounts;
    }

    @Override
    public Account findById(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null) {
            log.info("In findById account with id: {} is not found", id);
        } else {
            log.info("In findById account: {} found by id: {}", account, id);
        }
        return account;
    }

    @Override
    public void delete(long id) {
        accountRepository.deleteById(id);
        log.info("In delete account deleted by id: {}", id);    }

    @Override
    public Account save(Account account) {
        account = accountRepository.save(account);
        accountRepository.flush();
        log.info("In save account: {} successfully saved", account);
        return account;
    }

    @Override
    public Account update(Account account) {
        account.setUpdated(new Date());
        account = accountRepository.save(account);
        accountRepository.flush();
        log.info("In update account: {} successfully updated", account);
        return account;
    }
}
