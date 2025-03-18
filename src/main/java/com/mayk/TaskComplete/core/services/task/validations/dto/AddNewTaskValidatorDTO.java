package com.mayk.TaskComplete.core.services.task.validations.dto;

import com.mayk.TaskComplete.core.dto.TaskDTO;
import com.mayk.TaskComplete.core.model.Project;
import com.mayk.TaskComplete.core.model.User;

public record AddNewTaskValidatorDTO(
        Project project,
        User user,
        TaskDTO task
) { }
