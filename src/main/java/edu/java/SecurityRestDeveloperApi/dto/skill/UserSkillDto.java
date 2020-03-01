package edu.java.SecurityRestDeveloperApi.dto.skill;

import edu.java.SecurityRestDeveloperApi.model.Developer;
import edu.java.SecurityRestDeveloperApi.model.Skill;
import lombok.Data;

import java.util.List;

@Data
public class UserSkillDto {

    private String name;
    private List<Developer> developers;

    public static UserSkillDto toUserDto(Skill skill) {
        UserSkillDto skillDto = new UserSkillDto();
        skillDto.setName(skill.getName());
        skillDto.setDevelopers(skill.getDevelopers());
        return skillDto;
    }
}
