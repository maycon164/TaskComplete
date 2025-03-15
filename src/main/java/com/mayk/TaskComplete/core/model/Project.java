package com.mayk.TaskComplete.core.model;

import java.time.LocalDate;
import java.util.List;

public record Project (
        String name,
        String description,
        LocalDate createdAt,
        User createdBy,
        User projectOwner,
        List<Task> tasks
) { }
