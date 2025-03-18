package com.mayk.TaskComplete.core.service;

import com.mayk.TaskComplete.core.exception.TaskUpdateStatusException;
import com.mayk.TaskComplete.core.model.Task;
import com.mayk.TaskComplete.core.model.TaskStatus;
import com.mayk.TaskComplete.core.model.User;
import com.mayk.TaskComplete.core.ports.repository.TaskRepository;
import com.mayk.TaskComplete.core.services.TaskService;
import com.mayk.TaskComplete.core.validators.NotificationError;
import com.mayk.TaskComplete.core.validators.UpdateTaskValidator;
import com.mayk.TaskComplete.core.dto.TaskDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class TaskServiceTest {

    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UpdateTaskValidator updateTaskValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskService = new TaskService(taskRepository, updateTaskValidator);
    }

    @Test
    void givenUserAndTaskDTO_thenCreateTask() {
        User user = Mockito.mock();
        TaskDTO taskDTO = new TaskDTO("task01", "descricao01", TaskStatus.TODO);

        taskService.addNewTask(user, taskDTO);
        Mockito.verify(taskRepository).saveTask(Mockito.any());
    }

    @Test
    void givenTask_updateTaskStatus() {
        TaskStatus status = TaskStatus.IN_PROGRESS;
        Integer taskId = 1;
        Mockito.when(updateTaskValidator.validate(Mockito.any())).thenReturn(new NotificationError());

        Task task = taskService.updateTaskStatus(status, taskId);

        Mockito.verify(updateTaskValidator).validate(Mockito.any());
        Mockito.verify(taskRepository).getTaskById(taskId);
    }

    @Test
    void givenTask_updateTask_thenThrowException() {
        TaskStatus status = TaskStatus.IN_PROGRESS;
        Integer taskId = 1;

        var notification = new NotificationError();
        notification.addError("Task does not exist");

        Mockito.when(updateTaskValidator.validate(Mockito.any())).thenReturn(notification);

        Assertions.assertThrows(TaskUpdateStatusException.class, () -> {
            taskService.updateTaskStatus(status, taskId);
        });
    }
}
