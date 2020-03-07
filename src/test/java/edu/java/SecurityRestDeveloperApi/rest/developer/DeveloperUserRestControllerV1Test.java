package edu.java.SecurityRestDeveloperApi.rest.developer;

import edu.java.SecurityRestDeveloperApi.model.Developer;
import edu.java.SecurityRestDeveloperApi.service.DeveloperService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeveloperUserRestControllerV1Test {

    private DeveloperService developerService = mock(DeveloperService.class);

    private DeveloperModeratorRestControllerV1 developerModeratorRestControllerV1 = new DeveloperModeratorRestControllerV1(developerService);

    @Test
    void whenDevelopersListIsEmptyThenReturnNoContent() {
        when(developerService.getAll()).thenReturn(new ArrayList<>());
        ResponseEntity responseEntity = developerModeratorRestControllerV1.getAll();
        assertEquals("204 NO_CONTENT", responseEntity.getStatusCode().toString());
    }

    @Test
    void whenDevelopersListIsNotEmptyThenReturnOk() {
        Developer developer = new Developer();
        ArrayList<Developer> userList = mock(ArrayList.class);
        userList.add(developer);
        when(developerService.getAll()).thenReturn(userList);
        ResponseEntity responseEntity = developerModeratorRestControllerV1.getAll();
        assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }

    @Test
    void whenDeveloperWithId1IsExistsThenReturnOk() {
        Developer developer = new Developer();
        developer.setId(1L);
        when(developerService.findById(1L)).thenReturn(developer);
        ResponseEntity byId = developerModeratorRestControllerV1.getById(1L);
        assertEquals("200 OK", byId.getStatusCode().toString());
    }

    @Test
    void whenDeveloperWithId1IsNotExistsThenReturnNoContent() {
        when(developerService.findById(1L)).thenReturn(null);
        ResponseEntity byId = developerModeratorRestControllerV1.getById(1L);
        assertEquals("204 NO_CONTENT", byId.getStatusCode().toString());
    }
}