package edu.java.SecurityRestDeveloperApi.rest.account;

import edu.java.SecurityRestDeveloperApi.dto.account.ModeratorAccountDto;
import edu.java.SecurityRestDeveloperApi.model.Account;
import edu.java.SecurityRestDeveloperApi.model.Status;
import edu.java.SecurityRestDeveloperApi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/moderator/accounts")
public class AccountModeratorRestControllerV1 {

    private AccountService accountService;

    @Autowired
    public AccountModeratorRestControllerV1(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<Account> accounts = accountService.getAll();
        if (accounts == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Account account = accountService.findById(id);
        if (account == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity createAccount(@RequestBody ModeratorAccountDto accountDto) {
        Account account = accountDto.toAccount();
        account = accountService.save(account);
        return ResponseEntity.ok(account);
    }

    @PutMapping
    public ResponseEntity updateAccount(@RequestBody ModeratorAccountDto accountDto) {
        Account account = accountService.findById(accountDto.getId());
        if (account == null) {
            return ResponseEntity.badRequest().body("Account with id: " + accountDto.getId() + " not exist.");
        }
        account = accountDto.toAccount();
        accountService.update(account);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteAccount(@PathVariable("id") Long id) {
        Account account = accountService.findById(id);
        if (account == null) {
            return ResponseEntity.badRequest().body("Account with id: " + id + " not exist.");
        }
        account.setStatus(Status.DELETED);
        accountService.save(account);
        return ResponseEntity.ok().body("Account with id: " + id + " successfully deleted");
    }

}
