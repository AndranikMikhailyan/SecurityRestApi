package edu.java.SecurityRestDeveloperApi.rest.user;

import edu.java.SecurityRestDeveloperApi.dto.user.UserDto;
import edu.java.SecurityRestDeveloperApi.model.User;
import edu.java.SecurityRestDeveloperApi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminUserRestControllerV1Test {

    private UserService userService = mock(UserService.class);

    private AdminUserRestControllerV1 adminUserRestControllerV1 = new AdminUserRestControllerV1(userService);

    @Test
    void whenUsersListIsEmptyThenReturnNoContent() {
        when(userService.getAll()).thenReturn(new ArrayList<>());
        ResponseEntity responseEntity = adminUserRestControllerV1.getAll();
        assertEquals("204 NO_CONTENT", responseEntity.getStatusCode().toString());
    }

    @Test
    void whenUsersListIsNotEmptyThenReturnOk() {
        User user = new User();
        user.setRoles(new ArrayList<>());
        user.setPhones(new ArrayList<>());
        ArrayList<User> userList = mock(ArrayList.class);
        userList.add(user);
        when(userService.getAll()).thenReturn(userList);
        ResponseEntity responseEntity = adminUserRestControllerV1.getAll();
        assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }

    @Test
    void whenUserWithId1IsExistsThenReturnOk() {
        User user = new User();
        user.setId(1L);
        user.setRoles(new ArrayList<>());
        user.setPhones(new ArrayList<>());
        when(userService.findById(1L)).thenReturn(user);
        ResponseEntity byId = adminUserRestControllerV1.getById(1L);
        assertEquals("200 OK", byId.getStatusCode().toString());
    }

    @Test
    void whenUserWithId1IsNotExistsThenReturnNoContent() {
        when(userService.findById(1L)).thenReturn(null);
        ResponseEntity byId = adminUserRestControllerV1.getById(1L);
        assertEquals("204 NO_CONTENT", byId.getStatusCode().toString());
    }

    @Test
    void whenUserIsExistsThenReturnOk() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        when(userService.findById(userDto.getId())).thenReturn(new User());
        ResponseEntity responseEntity = adminUserRestControllerV1.updateUser(userDto);
        assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }

    @Test
    void whenUserIsExistsThenReturnBadRequest() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        when(userService.findById(userDto.getId())).thenReturn(null);
        ResponseEntity responseEntity = adminUserRestControllerV1.updateUser(userDto);
        assertEquals("400 BAD_REQUEST", responseEntity.getStatusCode().toString());
    }

    @Test
    void whenRemovableUserIsExistsThenReturnOk() {
        when(userService.findById(1L)).thenReturn(new User());
        ResponseEntity responseEntity = adminUserRestControllerV1.deleteUser(1L);
        assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }

    @Test
    void whenRemovableUserIsNotExistsThenReturnBadRequest() {
        when(userService.findById(1L)).thenReturn(null);
        ResponseEntity responseEntity = adminUserRestControllerV1.deleteUser(1L);
        assertEquals("400 BAD_REQUEST", responseEntity.getStatusCode().toString());
    }
}