package edu.java.SecurityRestDeveloperApi.dto.skill;

import edu.java.SecurityRestDeveloperApi.model.Developer;
import edu.java.SecurityRestDeveloperApi.model.Skill;
import edu.java.SecurityRestDeveloperApi.model.Status;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ModeratorSkillDto {

    private Long id;
    private Date created;
    private Date updated;
    private Status status;
    private String name;
    private List<Developer> developers;

    public Skill toSkill() {
        Skill skill = new Skill();
        Date created = new Date();
        skill.setCreated(created);
        skill.setUpdated(created);
        skill.setStatus(status);
        skill.setName(name);
        skill.setDevelopers(developers);
        return skill;
    }

    public static ModeratorSkillDto toSkillDto(Skill skill) {
        ModeratorSkillDto skillDto = new ModeratorSkillDto();
        skillDto.setId(skill.getId());
        skillDto.setCreated(skill.getCreated());
        skillDto.setUpdated(skill.getUpdated());
        skillDto.setStatus(skill.getStatus());
        skillDto.setName(skill.getName());
        skillDto.setDevelopers(skill.getDevelopers());
        return skillDto;
    }
}
