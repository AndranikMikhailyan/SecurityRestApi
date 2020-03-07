package edu.java.SecurityRestDeveloperApi.service.impl;

import edu.java.SecurityRestDeveloperApi.model.Account;
import edu.java.SecurityRestDeveloperApi.repository.AccountRepository;
import edu.java.SecurityRestDeveloperApi.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    AccountRepository accountRepository = mock(AccountRepository.class);
    AccountService accountService = new AccountServiceImpl(accountRepository);
    Account account = new Account();

    @BeforeEach
    public void setUp() {
        account.setDescription("description");
    }

    @Test
    void getAll() {
        accountService.getAll();
        verify(accountRepository, times(1)).findAll();
    }


    @Test
    void findById() {
        accountService.findById(1L);
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    void delete() {
        accountService.delete(1L);
        verify(accountRepository, times(1)).deleteById(1L);
    }

    @Test
    void save() {
        accountService.save(account);
        verify(accountRepository, times(1)).save(account);
        verify(accountRepository, times(1)).flush();
    }

    @Test
    void update() {
        accountService.update(account);
        verify(accountRepository, times(1)).save(account);
        verify(accountRepository, times(1)).flush();
    }
}