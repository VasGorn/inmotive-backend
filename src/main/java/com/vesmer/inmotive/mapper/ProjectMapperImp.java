package com.vesmer.inmotive.mapper;

import com.vesmer.inmotive.dto.ProjectDto;
import com.vesmer.inmotive.model.Project;
import com.vesmer.inmotive.model.User;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapperImp implements ProjectMapper {
    @Override
    public ProjectDto mapProjectToDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .created(project.getCreated())
                .supplyVoltage(project.getSupplyVoltage())
                .supplyFrequency(project.getSupplyFrequency())
                .maxSlip(project.getMaxSlip())
                .build();
    }

    @Override
    public Project mapDtoToProject(ProjectDto projectDto,
                                   User user) {
        return Project.builder()
                .id(projectDto.getId())
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .created(projectDto.getCreated())
                .supplyVoltage(projectDto.getSupplyVoltage())
                .supplyFrequency(projectDto.getSupplyFrequency())
                .maxSlip(projectDto.getMaxSlip())
                .user(user)
                .build();
    }
}
