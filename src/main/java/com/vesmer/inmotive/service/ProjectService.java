package com.vesmer.inmotive.service;

import com.vesmer.inmotive.mapper.ProjectMapper;
import com.vesmer.inmotive.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
