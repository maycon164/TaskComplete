package com.mayk.TaskComplete.core.model.builder;

import com.mayk.TaskComplete.core.model.Project;
import com.mayk.TaskComplete.core.model.Task;
import com.mayk.TaskComplete.core.model.User;

import java.time.LocalDate;
import java.util.List;

public class ProjectBuilder {
    private Long id;
    private String name;
    private String description;
    private LocalDate createdAt;
    private User createdBy;
    private User projectOwner;
    private List<Task> tasks;

    private ProjectBuilder() {}

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    public ProjectBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ProjectBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProjectBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProjectBuilder createdAt (LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ProjectBuilder createdBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public ProjectBuilder projectOwner(User projectOwner) {
        this.projectOwner = projectOwner;
        return this;
    }

    public ProjectBuilder tasks(List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public Project build(){
        return new Project(id, name, description, createdAt, createdBy, projectOwner, tasks);
    }
}
