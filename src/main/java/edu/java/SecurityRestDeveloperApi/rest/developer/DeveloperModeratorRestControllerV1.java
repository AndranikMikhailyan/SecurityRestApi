package edu.java.SecurityRestDeveloperApi.rest.developer;

import edu.java.SecurityRestDeveloperApi.dto.developer.ModeratorDeveloperDto;
import edu.java.SecurityRestDeveloperApi.model.Developer;
import edu.java.SecurityRestDeveloperApi.model.Status;
import edu.java.SecurityRestDeveloperApi.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/moderator/developers")
public class DeveloperModeratorRestControllerV1 {

    private final DeveloperService developerService;

    @Autowired
    public DeveloperModeratorRestControllerV1(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<Developer> developers = developerService.getAll();
        if (developers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(developers);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Developer developer = developerService.findById(id);
        if (developer == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(developer);
    }

    @PostMapping
    public ResponseEntity createDeveloper(@RequestBody ModeratorDeveloperDto developerDto) {
        Developer developer = developerDto.toDeveloper();
        developer = developerService.save(developer);
        return ResponseEntity.ok(developer);
    }

    @PutMapping
    public ResponseEntity updateDeveloper(@RequestBody ModeratorDeveloperDto developerDto) {
        Developer developer = developerService.findById(developerDto.getId());
        if (developer == null) {
            return ResponseEntity.badRequest().body("Developer with id: " + developerDto.getId() + " not exist.");
        }
        developer = developerDto.toDeveloper();
        developerService.update(developer);
        return ResponseEntity.ok(developer);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteDeveloper(@PathVariable("id") Long id) {
        Developer developer = developerService.findById(id);
        if (developer == null) {
            return ResponseEntity.badRequest().body("Developer with id: " + id + " not exist.");
        }
        developer.setStatus(Status.DELETED);
        developerService.save(developer);
        return ResponseEntity.ok().body("Developer with id: " + id + " successfully deleted");
    }
}
