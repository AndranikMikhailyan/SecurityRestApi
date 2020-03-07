package edu.java.SecurityRestDeveloperApi.rest.skill;

import edu.java.SecurityRestDeveloperApi.model.Skill;
import edu.java.SecurityRestDeveloperApi.service.SkillService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SkillUserRestControllerV1Test {
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
}