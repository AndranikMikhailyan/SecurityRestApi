package edu.java.SecurityRestDeveloperApi.rest.skill;

import edu.java.SecurityRestDeveloperApi.dto.account.ModeratorAccountDto;
import edu.java.SecurityRestDeveloperApi.dto.skill.ModeratorSkillDto;
import edu.java.SecurityRestDeveloperApi.model.Account;
import edu.java.SecurityRestDeveloperApi.model.Skill;
import edu.java.SecurityRestDeveloperApi.model.Status;
import edu.java.SecurityRestDeveloperApi.service.AccountService;
import edu.java.SecurityRestDeveloperApi.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/moderator/skills")
public class SkillModeratorRestControllerV1 {

    private SkillService skillService;

    @Autowired
    public SkillModeratorRestControllerV1(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<Skill> skills = skillService.getAll();
        if (skills == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Skill skill = skillService.findById(id);
        if (skill == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(skill);
    }

    @PostMapping
    public ResponseEntity createSkill(@RequestBody ModeratorSkillDto skillDto) {
        Skill skill = skillDto.toSkill();
        skill = skillService.save(skill);
        return ResponseEntity.ok(skill);
    }

    @PutMapping
    public ResponseEntity updateSkill(@RequestBody ModeratorSkillDto skillDto) {
        Skill skill = skillService.findById(skillDto.getId());
        if (skill == null) {
            return ResponseEntity.badRequest().body("Skill with id: " + skillDto.getId() + " not exist.");
        }
        skill = skillDto.toSkill();
        skillService.update(skill);
        return ResponseEntity.ok(skill);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteSkill(@PathVariable("id") Long id) {
        Skill skill = skillService.findById(id);
        if (skill == null) {
            return ResponseEntity.badRequest().body("Skill with id: " + id + " not exist.");
        }
        skill.setStatus(Status.DELETED);
        skillService.save(skill);
        return ResponseEntity.ok().body("Skill with id: " + id + " successfully deleted");
    }

}
