package edu.java.SecurityRestDeveloperApi.dto.developer;

import edu.java.SecurityRestDeveloperApi.model.Account;
import edu.java.SecurityRestDeveloperApi.model.Developer;
import edu.java.SecurityRestDeveloperApi.model.Skill;
import edu.java.SecurityRestDeveloperApi.model.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class ModeratorDeveloperDto {

    private Long id;
    private Date created;
    private Date updated;
    private Status status;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private LocalDate hiringDate;
    private BigDecimal salary;
    private List<Skill> skills;
    private Account account;

    public Developer toDeveloper() {
        Developer developer = new Developer();
        Date created = new Date();
        developer.setCreated(created);
        developer.setUpdated(created);
        developer.setStatus(status);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setBirthDay(birthDay);
        developer.setHiringDate(hiringDate);
        developer.setSalary(salary);
        developer.setSkills(skills);
        developer.setAccount(account);
        return developer;
    }

    public static ModeratorDeveloperDto toDeveloperDto(Developer developer) {
        ModeratorDeveloperDto developerDto = new ModeratorDeveloperDto();
        developerDto.setId(developer.getId());
        developerDto.setCreated(developer.getCreated());
        developerDto.setUpdated(developer.getUpdated());
        developerDto.setStatus(developer.getStatus());
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
