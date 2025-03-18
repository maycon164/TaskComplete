package com.mayk.TaskComplete.core.service.task;

import com.mayk.TaskComplete.core.exception.AddNewTaskException;
import com.mayk.TaskComplete.core.exception.ProjectNotFoundException;
import com.mayk.TaskComplete.core.exception.TaskUpdateStatusException;
import com.mayk.TaskComplete.core.model.Project;
import com.mayk.TaskComplete.core.model.Task;
import com.mayk.TaskComplete.core.model.TaskStatus;
import com.mayk.TaskComplete.core.model.User;
import com.mayk.TaskComplete.core.model.builder.ProjectBuilder;
import com.mayk.TaskComplete.core.ports.repository.ProjectRepository;
import com.mayk.TaskComplete.core.ports.repository.TaskRepository;
import com.mayk.TaskComplete.core.services.task.TaskService;
import com.mayk.TaskComplete.core.validators.NotificationError;
import com.mayk.TaskComplete.core.services.task.validations.TaskValidator;
import com.mayk.TaskComplete.core.dto.TaskDTO;
import org.checkerframework.checker.units.qual.N;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class TaskServiceTest {

    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskValidator taskValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskService = new TaskService(projectRepository, taskRepository, taskValidator);
    }

    @Test
    void givenUserAndProjectIdAndTaskDTO_thenCreateTask() {
        User user = Mockito.mock();
        TaskDTO taskDTO = new TaskDTO("task01", "descricao01", TaskStatus.TODO);
        Long projectId = 1L;

        Project project = ProjectBuilder.builder().build();

        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        taskService.addNewTask(user, projectId, taskDTO);
        Mockito.verify(taskRepository).saveTask(Mockito.any(Project.class), Mockito.any(Task.class));
    }

    @Test
    void givenUserProjectIdAndTaskDTO_thenProjectNotFound() {
        User user = Mockito.mock();
        TaskDTO taskDTO = new TaskDTO("task01", "descricao01", TaskStatus.TODO);
        Long projectId = 1L;

        Assertions.assertThrows(ProjectNotFoundException.class, () -> {
            taskService.addNewTask(user, projectId, taskDTO);
        });

    }

    @Test
    void givenUserProjectIdAndTaskDTO_thenThrowAddNewTaskException() {
        User user = Mockito.mock();
        TaskDTO taskDTO = new TaskDTO("task01", "descricao01", TaskStatus.TODO);
        Long projectId = 1L;

        Project project = ProjectBuilder.builder().build();

        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        var notification = new NotificationError();
        notification.addError("teste");
        Mockito.when(taskValidator.validateAddNewTask(Mockito.any())).thenReturn(notification);

        Assertions.assertThrows(AddNewTaskException.class, () -> {
           taskService.addNewTask(user, projectId, taskDTO);
        });

    }

    @Test
    void givenTask_updateTaskStatus() {
        TaskStatus status = TaskStatus.IN_PROGRESS;
        Integer taskId = 1;
        Mockito.when(taskValidator.validate(Mockito.any())).thenReturn(new NotificationError());

        Task task = taskService.updateTaskStatus(status, taskId);

        Mockito.verify(taskValidator).validate(Mockito.any());
        Mockito.verify(taskRepository).getTaskById(taskId);
    }

    @Test
    void givenTask_updateTask_thenThrowException() {
        TaskStatus status = TaskStatus.IN_PROGRESS;
        Integer taskId = 1;

        var notification = new NotificationError();
        notification.addError("Task does not exist");

        Mockito.when(taskValidator.validate(Mockito.any())).thenReturn(notification);

        Assertions.assertThrows(TaskUpdateStatusException.class, () -> {
            taskService.updateTaskStatus(status, taskId);
        });
    }
}
