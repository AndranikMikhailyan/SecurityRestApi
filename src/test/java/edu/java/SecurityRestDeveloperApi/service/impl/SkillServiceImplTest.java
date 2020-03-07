package edu.java.SecurityRestDeveloperApi.service.impl;

import edu.java.SecurityRestDeveloperApi.model.Skill;
import edu.java.SecurityRestDeveloperApi.repository.SkillRepository;
import edu.java.SecurityRestDeveloperApi.service.SkillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class SkillServiceImplTest {

    SkillRepository skillRepository = mock(SkillRepository.class);
    SkillService skillService = new SkillServiceImpl(skillRepository);
    Skill skill = new Skill();

    @BeforeEach
    public void setUp() {
        skill.setName("skillName");
    }

    @Test
    void getAll() {
        skillService.getAll();
        verify(skillRepository, times(1)).findAll();
    }


    @Test
    void findById() {
        skillService.findById(1L);
        verify(skillRepository, times(1)).findById(1L);
    }

    @Test
    void delete() {
        skillService.delete(1L);
        verify(skillRepository, times(1)).deleteById(1L);
    }

    @Test
    void save() {
        skillService.save(skill);
        verify(skillRepository, times(1)).save(skill);
        verify(skillRepository, times(1)).flush();
    }

    @Test
    void update() {
        skillService.update(skill);
        verify(skillRepository, times(1)).save(skill);
        verify(skillRepository, times(1)).flush();
    }
}