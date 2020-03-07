package edu.java.SecurityRestDeveloperApi.rest.developer;

import edu.java.SecurityRestDeveloperApi.dto.developer.UserDeveloperDto;
import edu.java.SecurityRestDeveloperApi.model.Developer;
import edu.java.SecurityRestDeveloperApi.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/user/developers")
public class DeveloperUserRestControllerV1 {

    private final DeveloperService developerService;

    @Autowired
    public DeveloperUserRestControllerV1(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<Developer> developers = developerService.getAll();
        if (developers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<UserDeveloperDto> developersDto = developers.stream().map(UserDeveloperDto::toDeveloperDto).collect(Collectors.toList());
        return ResponseEntity.ok(developersDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Developer developer = developerService.findById(id);
        if (developer == null) {
            return ResponseEntity.noContent().build();
        }
        UserDeveloperDto developerDto = UserDeveloperDto.toDeveloperDto(developer);
        return ResponseEntity.ok(developerDto);
    }
}
