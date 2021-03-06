package com.vesmer.inmotive.controller;

import com.vesmer.inmotive.dto.ProjectDto;
import com.vesmer.inmotive.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<Collection<ProjectDto>> getAllForUser() {
        return ResponseEntity.status(OK).body(projectService.getAllForUser());
    }

    @PostMapping
    public ResponseEntity<ProjectDto> saveProject(@RequestBody ProjectDto projectDto) {
        return ResponseEntity.status(CREATED).body(projectService.save(projectDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.delete(id);
        return new ResponseEntity<>(OK);
    }
}
