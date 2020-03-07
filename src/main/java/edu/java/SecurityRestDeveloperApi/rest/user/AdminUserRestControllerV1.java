package edu.java.SecurityRestDeveloperApi.rest.user;

import edu.java.SecurityRestDeveloperApi.dto.user.UserDto;
import edu.java.SecurityRestDeveloperApi.model.Status;
import edu.java.SecurityRestDeveloperApi.model.User;
import edu.java.SecurityRestDeveloperApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/admin/users")
public class AdminUserRestControllerV1 {

    private final UserService userService;


    @Autowired
    public AdminUserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<UserDto> userDtoList = users.stream().map(UserDto::toUserDto).collect(Collectors.toList());
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        UserDto userDto = UserDto.toUserDto(user);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UserDto userDto) {
        User user = userService.findById(userDto.getId());
        if (user == null) {
            return ResponseEntity.badRequest().body("User with id: " + userDto.getId() + " not exist.");
        }
        user = userDto.toUser();
        userService.update(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.badRequest().body("User with id: " + id + " not exist.");
        }
        user.setStatus(Status.DELETED);
        userService.save(user);
        return ResponseEntity.ok().body("User with id: " + id + " successfully deleted");
    }
}
