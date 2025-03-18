package com.mayk.TaskComplete.core.ports.repository;

import com.mayk.TaskComplete.core.model.Project;

import java.util.Optional;

public interface ProjectRepository {

    Project saveProject(Project project);

    Optional<Project> findById(Long id);

}
