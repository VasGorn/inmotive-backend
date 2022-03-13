package com.vesmer.inmotive.service;

import com.vesmer.inmotive.dto.ProjectDto;
import com.vesmer.inmotive.mapper.ProjectMapper;
import com.vesmer.inmotive.model.Project;
import com.vesmer.inmotive.model.User;
import com.vesmer.inmotive.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final AuthService authService;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          AuthService authService, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.authService = authService;
        this.projectMapper = projectMapper;
    }

    public Collection<ProjectDto> getAllForUser() {
        User user = authService.getCurrentUser();
        Collection<Project> projects = projectRepository.findByUser(user);
        return projects.stream()
                .map(projectMapper::mapProjectToDto)
                .collect(Collectors.toList());
    }

    public ProjectDto save(ProjectDto projectDto) {
        User user = authService.getCurrentUser();
        projectDto.setCreated(Instant.now());
        Project savedProject = projectRepository.save(
                projectMapper.mapDtoToProject(projectDto, user)
        );
        projectDto.setId(savedProject.getId());
        return projectDto;
    }
}
