package com.mayk.TaskComplete.infraestructure.outbound.adapters.repository;

import com.mayk.TaskComplete.core.model.Project;
import com.mayk.TaskComplete.core.ports.repository.ProjectRepository;

import java.util.Optional;

public class ProjectRepositoryImpl implements ProjectRepository {
    @Override
    public Project saveProject(Project project) {
        return null;
    }

    @Override
    public Optional<Project> findById(Long id) {
        return Optional.empty();
    }
}
