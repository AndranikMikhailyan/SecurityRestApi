package edu.java.SecurityRestDeveloperApi.service;

import edu.java.SecurityRestDeveloperApi.model.Skill;

import java.util.List;

public interface SkillService {

    List<Skill> getAll();

    Skill findById(Long id);

    void delete(long id);

    Skill save(Skill skill);

    Skill update(Skill skill);
}
