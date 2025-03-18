package com.mayk.TaskComplete.core.dto;

import com.mayk.TaskComplete.core.model.TaskStatus;

public record TaskDTO (
        String name,
        String description,
        TaskStatus status
) {
}
