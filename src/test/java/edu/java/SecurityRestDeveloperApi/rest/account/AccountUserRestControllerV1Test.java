package edu.java.SecurityRestDeveloperApi.rest.account;

import edu.java.SecurityRestDeveloperApi.model.Account;
import edu.java.SecurityRestDeveloperApi.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountUserRestControllerV1Test {

    private AccountService accountService = mock(AccountService.class);

    private AccountModeratorRestControllerV1 accountModeratorRestControllerV1 = new AccountModeratorRestControllerV1(accountService);

    @Test
    void whenAccountsListIsEmptyThenReturnNoContent() {
        when(accountService.getAll()).thenReturn(new ArrayList<>());
        ResponseEntity responseEntity = accountModeratorRestControllerV1.getAll();
        assertEquals("204 NO_CONTENT", responseEntity.getStatusCode().toString());
    }

    @Test
    void whenAccountsListIsNotEmptyThenReturnOk() {
        Account account = new Account();
        ArrayList<Account> userList = mock(ArrayList.class);
        userList.add(account);
        when(accountService.getAll()).thenReturn(userList);
        ResponseEntity responseEntity = accountModeratorRestControllerV1.getAll();
        assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }

    @Test
    void whenAccountWithId1IsExistsThenReturnOk() {
        Account account = new Account();
        account.setId(1L);
        when(accountService.findById(1L)).thenReturn(account);
        ResponseEntity byId = accountModeratorRestControllerV1.getById(1L);
        assertEquals("200 OK", byId.getStatusCode().toString());
    }

    @Test
    void whenAccountWithId1IsNotExistsThenReturnNoContent() {
        when(accountService.findById(1L)).thenReturn(null);
        ResponseEntity byId = accountModeratorRestControllerV1.getById(1L);
        assertEquals("204 NO_CONTENT", byId.getStatusCode().toString());
    }
}