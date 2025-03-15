package com.mayk.TaskComplete.core.services;

import com.mayk.TaskComplete.core.model.Project;
import com.mayk.TaskComplete.core.model.User;
import com.mayk.TaskComplete.core.repository.ProjectRepository;
import com.mayk.TaskComplete.infra.dto.ProjectDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project addNewProject(User user, ProjectDTO projectDTO) {
        LocalDate createdAt = LocalDate.now();
        Project project = new Project(projectDTO.name(), projectDTO.description(), createdAt, user, user, List.of());

        return projectRepository.saveProject(project);
    }

}
