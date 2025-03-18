package com.mayk.TaskComplete.core.model;

import java.time.LocalDate;

public record Task (
    Long id,
    String name,
    String description,
    TaskStatus taskStatus,
    User assignTo,
    User createdBy,
    LocalDate createdAt
) { }
