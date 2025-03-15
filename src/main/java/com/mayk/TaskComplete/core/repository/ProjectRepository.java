package com.mayk.TaskComplete.core.repository;

import com.mayk.TaskComplete.core.model.Project;

public interface ProjectRepository {

    Project saveProject(Project project);

    Project getProjectById(Integer id);

}
