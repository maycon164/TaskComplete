package com.mayk.TaskComplete.core.exception;

public class TaskNotFoundException extends NotFound{

    public TaskNotFoundException() {
        super("Task not found");
    }
}
