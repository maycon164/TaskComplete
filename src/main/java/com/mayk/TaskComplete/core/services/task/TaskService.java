package com.mayk.TaskComplete.core.services.task;

import com.mayk.TaskComplete.core.exception.AddNewTaskException;
import com.mayk.TaskComplete.core.exception.ProjectNotFoundException;
import com.mayk.TaskComplete.core.exception.TaskUpdateStatusException;
import com.mayk.TaskComplete.core.model.Project;
import com.mayk.TaskComplete.core.model.Task;
import com.mayk.TaskComplete.core.model.TaskStatus;
import com.mayk.TaskComplete.core.model.User;
import com.mayk.TaskComplete.core.model.builder.TaskBuilder;
import com.mayk.TaskComplete.core.ports.repository.ProjectRepository;
import com.mayk.TaskComplete.core.ports.repository.TaskRepository;
import com.mayk.TaskComplete.core.services.project.ProjectService;
import com.mayk.TaskComplete.core.services.task.validations.dto.AddNewTaskValidatorDTO;
import com.mayk.TaskComplete.core.validators.NotificationError;
import com.mayk.TaskComplete.core.services.task.validations.TaskValidator;
import com.mayk.TaskComplete.core.services.task.validations.dto.UpdateTaskValidatorDTO;
import com.mayk.TaskComplete.core.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskValidator taskValidator;

    public TaskService(ProjectRepository projectRepository, TaskRepository taskRepository, TaskValidator taskValidator) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.taskValidator = taskValidator;
    }

    public Task addNewTask(User user, Long projectId, TaskDTO taskDTO) {

        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        NotificationError notificationError = taskValidator.validateAddNewTask(new AddNewTaskValidatorDTO(
                project,
                user,
                taskDTO
        ));

        if(notificationError.hasErrors()) {
            throw new AddNewTaskException(notificationError.getErrors());
        }

        LocalDate createdAt = LocalDate.now();

        Task task = TaskBuilder.builder()
                .name(taskDTO.name())
                .description(taskDTO.description())
                .taskStatus(taskDTO.status())
                .createdBy(user)
                .createdAt(createdAt)
                .build();

        return taskRepository.saveTask(project, task);
    }

    public Task updateTaskStatus(TaskStatus taskStatus, Integer taskId){
        Task task = taskRepository.getTaskById(taskId);

        NotificationError notificationError = taskValidator.validate(new UpdateTaskValidatorDTO(task, taskStatus));

        if(notificationError.hasErrors()) {
            throw new TaskUpdateStatusException(notificationError.getErrors());
        }

        return task;
    }

    public Task assignUser(User user, Integer taskId) {
        return TaskBuilder.builder().build();
    }
}
