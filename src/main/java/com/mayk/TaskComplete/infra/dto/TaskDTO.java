package com.mayk.TaskComplete.infra.dto;

import com.mayk.TaskComplete.core.model.TaskStatus;

public record TaskDTO (
        String name,
        String description,
        TaskStatus status
) {
}
