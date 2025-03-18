package com.mayk.TaskComplete.core.ports.repository;

import com.mayk.TaskComplete.core.model.Project;
import com.mayk.TaskComplete.core.model.Task;
import com.mayk.TaskComplete.core.model.TaskStatus;

import java.util.Optional;

public interface TaskRepository {

    Task saveTask(Project project, Task task);

    Optional<Task> findTaskById(Long taskId);

    void updateStatusTask(Long taskId, TaskStatus taskStatus);
}
