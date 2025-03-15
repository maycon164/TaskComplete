package com.mayk.TaskComplete.core.model.builder;

import com.mayk.TaskComplete.core.model.Task;
import com.mayk.TaskComplete.core.model.TaskStatus;
import com.mayk.TaskComplete.core.model.User;

import java.time.LocalDate;

public class TaskBuilder {

    private String name;
    private String description;
    private TaskStatus taskStatus;
    private User assignTo;
    private User createdBy;
    private LocalDate createdAt;

    private TaskBuilder() {
    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    public TaskBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TaskBuilder description(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder taskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
        return this;
    }

    public TaskBuilder assignTo(User assignTo) {
        this.assignTo = assignTo;
        return this;
    }

    public TaskBuilder createdBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public TaskBuilder createdAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Task build() {
        return new Task(name, description, taskStatus, assignTo, createdBy, createdAt);
    }
}
