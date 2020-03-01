package edu.java.SecurityRestDeveloperApi.rest.account;

import edu.java.SecurityRestDeveloperApi.dto.account.UserAccountDto;
import edu.java.SecurityRestDeveloperApi.model.Account;
import edu.java.SecurityRestDeveloperApi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/user/accounts")
public class AccountUserRestControllerV1 {

    private AccountService accountService;

    @Autowired
    public AccountUserRestControllerV1(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<Account> accounts = accountService.getAll();
        if (accounts == null) {
            return ResponseEntity.noContent().build();
        }
        List<UserAccountDto> accountsDto = accounts.stream().map(UserAccountDto::toAccountDto).collect(Collectors.toList());
        return ResponseEntity.ok(accountsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Account account = accountService.findById(id);
        if (account == null) {
            return ResponseEntity.noContent().build();
        }
        UserAccountDto accountDto = UserAccountDto.toAccountDto(account);
        return ResponseEntity.ok(accountDto);
    }
}
