package com.vesmer.inmotive.mapper;

import com.vesmer.inmotive.dto.ProjectDto;
import com.vesmer.inmotive.model.Project;
import com.vesmer.inmotive.model.User;

public interface ProjectMapper {
    ProjectDto mapProjectToDto(Project project);
    Project mapDtoToProject(ProjectDto projectDto, User user);
}
