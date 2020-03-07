package edu.java.SecurityRestDeveloperApi.rest.skill;

import edu.java.SecurityRestDeveloperApi.dto.skill.UserSkillDto;
import edu.java.SecurityRestDeveloperApi.model.Skill;
import edu.java.SecurityRestDeveloperApi.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/user/skills")
public class SkillUserRestControllerV1 {

    private SkillService skillService;

    @Autowired
    public SkillUserRestControllerV1(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<Skill> skills = skillService.getAll();
        if (skills.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<UserSkillDto> skillsDto = skills.stream().map(UserSkillDto::toUserDto).collect(Collectors.toList());
        return ResponseEntity.ok(skillsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Skill skill = skillService.findById(id);
        if (skill == null) {
            return ResponseEntity.noContent().build();
        }
        UserSkillDto skillDto = UserSkillDto.toUserDto(skill);
        return ResponseEntity.ok(skillDto);
    }
}
