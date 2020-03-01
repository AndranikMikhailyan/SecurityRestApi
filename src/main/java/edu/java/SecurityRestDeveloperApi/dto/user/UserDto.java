package edu.java.SecurityRestDeveloperApi.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.java.SecurityRestDeveloperApi.model.Phone;
import edu.java.SecurityRestDeveloperApi.model.Role;
import edu.java.SecurityRestDeveloperApi.model.Status;
import edu.java.SecurityRestDeveloperApi.model.User;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Long id;
    private Date created;
    private Date updated;
    private Status status;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> roles;
    private List<String> phones;

    public User toUser(){
        User user = new User();
        Date created = new Date();
        user.setCreated(created);
        user.setUpdated(created);
        user.setStatus(status);
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setCreated(user.getCreated());
        userDto.setUpdated(user.getUpdated());
        userDto.setStatus(user.getStatus());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        List<String> collectRoles = user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
        userDto.setRoles(collectRoles);
        List<String> collectPhones = user.getPhones().stream().map(phone -> phone.getPhoneNumber()).collect(Collectors.toList());
        userDto.setPhones(collectPhones);
        return userDto;
    }
}
