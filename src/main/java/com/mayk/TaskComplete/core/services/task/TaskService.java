package com.mayk.TaskComplete.core.services.task;

import com.mayk.TaskComplete.core.exception.TaskUpdateStatusException;
import com.mayk.TaskComplete.core.model.Task;
import com.mayk.TaskComplete.core.model.TaskStatus;
import com.mayk.TaskComplete.core.model.User;
import com.mayk.TaskComplete.core.model.builder.TaskBuilder;
import com.mayk.TaskComplete.core.ports.repository.TaskRepository;
import com.mayk.TaskComplete.core.validators.NotificationError;
import com.mayk.TaskComplete.core.validators.UpdateTaskValidator;
import com.mayk.TaskComplete.core.validators.UpdateTaskValidatorDTO;
import com.mayk.TaskComplete.core.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UpdateTaskValidator updateTaskValidator;

    public TaskService(TaskRepository taskRepository, UpdateTaskValidator updateTaskValidator) {
        this.taskRepository = taskRepository;
        this.updateTaskValidator = updateTaskValidator;
    }

    public Task addNewTask(User user, TaskDTO taskDTO) {
        LocalDate createdAt = LocalDate.now();

        Task task = TaskBuilder.builder()
                .name(taskDTO.name())
                .description(taskDTO.description())
                .taskStatus(taskDTO.status())
                .createdBy(user)
                .createdAt(createdAt)
                .build();

        return taskRepository.saveTask(task);
    }

    public Task updateTaskStatus(TaskStatus taskStatus, Integer taskId){
        Task task = taskRepository.getTaskById(taskId);

        NotificationError notificationError = updateTaskValidator.validate(new UpdateTaskValidatorDTO(task, taskStatus));

        if(notificationError.hasErrors()) {
            throw new TaskUpdateStatusException(notificationError.getErrors());
        }

        return task;
    }

    public Task assignUser(User user, Integer taskId) {
        return TaskBuilder.builder().build();
    }
}
