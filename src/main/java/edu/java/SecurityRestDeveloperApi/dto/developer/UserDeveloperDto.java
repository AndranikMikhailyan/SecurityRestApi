package edu.java.SecurityRestDeveloperApi.dto.developer;

import edu.java.SecurityRestDeveloperApi.model.Account;
import edu.java.SecurityRestDeveloperApi.model.Developer;
import edu.java.SecurityRestDeveloperApi.model.Skill;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class UserDeveloperDto {

    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private LocalDate hiringDate;
    private BigDecimal salary;
    private List<Skill> skills;
    private Account account;

    public static UserDeveloperDto toDeveloperDto(Developer developer) {
        UserDeveloperDto developerDto = new UserDeveloperDto();
        developerDto.setFirstName(developer.getFirstName());
        developerDto.setLastName(developer.getLastName());
        developerDto.setBirthDay(developer.getBirthDay());
        developerDto.setHiringDate(developer.getHiringDate());
        developerDto.setSalary(developer.getSalary());
        developerDto.setSkills(developer.getSkills());
        developerDto.setAccount(developer.getAccount());
        return developerDto;
    }
}
