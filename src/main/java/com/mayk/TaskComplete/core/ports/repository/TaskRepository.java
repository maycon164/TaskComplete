package com.mayk.TaskComplete.core.ports.repository;

import com.mayk.TaskComplete.core.model.Task;
import com.mayk.TaskComplete.core.model.TaskStatus;

public interface TaskRepository {

    Task saveTask(Task task);

    Task getTaskById(Integer taskId);

    void updateStatusTask(Integer taskId, TaskStatus taskStatus);
}
