package edu.java.SecurityRestDeveloperApi.dto.account;

import edu.java.SecurityRestDeveloperApi.model.Account;
import edu.java.SecurityRestDeveloperApi.model.Developer;
import edu.java.SecurityRestDeveloperApi.model.Status;
import lombok.Data;

import java.util.Date;

@Data
public class ModeratorAccountDto {

    private Long id;
    private Date created;
    private Date updated;
    private Status status;
    private Developer developer;
    private String description;


    public Account toAccount() {
        Account account = new Account();
        account.setCreated(new Date());
        account.setUpdated(new Date());
        account.setStatus(status);
        account.setDeveloper(developer);
        account.setDescription(description);
        return account;
    }

    public static ModeratorAccountDto toAccountDto(Account account) {
        ModeratorAccountDto accountDto = new ModeratorAccountDto();
        accountDto.setId(account.getId());
        accountDto.setCreated(account.getCreated());
        accountDto.setUpdated(account.getUpdated());
        accountDto.setStatus(account.getStatus());
        accountDto.setDeveloper(account.getDeveloper());
        accountDto.setDescription(account.getDescription());
        return accountDto;
    }
}
