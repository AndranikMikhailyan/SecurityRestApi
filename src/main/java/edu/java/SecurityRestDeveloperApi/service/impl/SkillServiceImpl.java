package edu.java.SecurityRestDeveloperApi.service.impl;

import edu.java.SecurityRestDeveloperApi.model.Skill;
import edu.java.SecurityRestDeveloperApi.repository.SkillRepository;
import edu.java.SecurityRestDeveloperApi.service.SkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository accountRepository) {
        this.skillRepository = accountRepository;
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> skills= skillRepository.findAll();
        log.info("In getAll {} skills found", skills.size());
        return skills;
    }

    @Override
    public Skill findById(Long id) {
        Skill skill = skillRepository.findById(id).orElse(null);
        if (skill == null) {
            log.info("In findById skill with id: {} is not found", id);
        } else {
            log.info("In findById skill: {} found by id: {}", skill, id);
        }
        return skill;
    }

    @Override
    public void delete(long id) {
        skillRepository.deleteById(id);
        log.info("In delete skill deleted by id: {}", id);    }

    @Override
    public Skill save(Skill skill) {
        skill = skillRepository.save(skill);
        skillRepository.flush();
        log.info("In save skill: {} successfully saved", skill);
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        skill.setUpdated(new Date());
        skill = skillRepository.save(skill);
        skillRepository.flush();
        log.info("In update skill: {} successfully updated", skill);
        return skill;
    }
}
