package edu.java.SecurityRestDeveloperApi.dto.account;

import edu.java.SecurityRestDeveloperApi.model.Account;
import edu.java.SecurityRestDeveloperApi.model.Developer;
import lombok.Data;

@Data
public class UserAccountDto {

    private Developer developer;
    private String description;

    public static UserAccountDto toAccountDto(Account account) {
        UserAccountDto accountDto = new UserAccountDto();
        accountDto.setDeveloper(account.getDeveloper());
        accountDto.setDescription(account.getDescription());
        return accountDto;
    }
}
