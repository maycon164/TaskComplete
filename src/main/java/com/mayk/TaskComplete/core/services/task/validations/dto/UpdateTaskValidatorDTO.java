package com.mayk.TaskComplete.core.services.task.validations.dto;

import com.mayk.TaskComplete.core.model.Task;
import com.mayk.TaskComplete.core.model.TaskStatus;

public record UpdateTaskValidatorDTO (
        Task task,
        TaskStatus taskStatus
) { }
