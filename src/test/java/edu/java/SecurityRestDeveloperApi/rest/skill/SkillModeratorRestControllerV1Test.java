package edu.java.SecurityRestDeveloperApi.rest.skill;

import edu.java.SecurityRestDeveloperApi.dto.skill.ModeratorSkillDto;
import edu.java.SecurityRestDeveloperApi.model.Skill;
import edu.java.SecurityRestDeveloperApi.service.SkillService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SkillModeratorRestControllerV1Test {

    private SkillService skillService = mock(SkillService.class);

    private SkillModeratorRestControllerV1 skillModeratorRestControllerV1 = new SkillModeratorRestControllerV1(skillService);

    @Test
    void whenSkillsListIsEmptyThenReturnNoContent() {
        when(skillService.getAll()).thenReturn(new ArrayList<>());
        ResponseEntity responseEntity = skillModeratorRestControllerV1.getAll();
        assertEquals("204 NO_CONTENT", responseEntity.getStatusCode().toString());
    }

    @Test
    void whenSkillsListIsNotEmptyThenReturnOk() {
        Skill skill = new Skill();
        ArrayList<Skill> userList = mock(ArrayList.class);
        userList.add(skill);
        when(skillService.getAll()).thenReturn(userList);
        ResponseEntity responseEntity = skillModeratorRestControllerV1.getAll();
        assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }

    @Test
    void whenSkillWithId1IsExistsThenReturnOk() {
        Skill skill = new Skill();
        skill.setId(1L);
        when(skillService.findById(1L)).thenReturn(skill);
        ResponseEntity byId = skillModeratorRestControllerV1.getById(1L);
        assertEquals("200 OK", byId.getStatusCode().toString());
    }

    @Test
    void whenSkillWithId1IsNotExistsThenReturnNoContent() {
        when(skillService.findById(1L)).thenReturn(null);
        ResponseEntity byId = skillModeratorRestControllerV1.getById(1L);
        assertEquals("204 NO_CONTENT", byId.getStatusCode().toString());
    }

    @Test
    void whenCreateSkillThenReturnOk() {
        ModeratorSkillDto skillDto = new ModeratorSkillDto();
        skillDto.setId(1L);
        ResponseEntity responseEntity = skillModeratorRestControllerV1.createSkill(skillDto);
        assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }

    @Test
    void whenSkillIsExistsThenReturnOk() {
        ModeratorSkillDto skillDto = new ModeratorSkillDto();
        skillDto.setId(1L);
        when(skillService.findById(skillDto.getId())).thenReturn(new Skill());
        ResponseEntity responseEntity = skillModeratorRestControllerV1.updateSkill(skillDto);
        assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }

    @Test
    void whenSkillIsExistsThenReturnBadRequest() {
        ModeratorSkillDto skillDto = new ModeratorSkillDto();
        skillDto.setId(1L);
        when(skillService.findById(skillDto.getId())).thenReturn(null);
        ResponseEntity responseEntity = skillModeratorRestControllerV1.updateSkill(skillDto);
        assertEquals("400 BAD_REQUEST", responseEntity.getStatusCode().toString());
    }

    @Test
    void whenRemovableSkillIsExistsThenReturnOk() {
        when(skillService.findById(1L)).thenReturn(new Skill());
        ResponseEntity responseEntity = skillModeratorRestControllerV1.deleteSkill(1L);
        assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }

    @Test
    void whenRemovableSkillIsNotExistsThenReturnBadRequest() {
        when(skillService.findById(1L)).thenReturn(null);
        ResponseEntity responseEntity = skillModeratorRestControllerV1.deleteSkill(1L);
        assertEquals("400 BAD_REQUEST", responseEntity.getStatusCode().toString());
    }
}