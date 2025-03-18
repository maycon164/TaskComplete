package com.mayk.TaskComplete.core.exception;

public class ProjectNotFoundException extends NotFound{

    public ProjectNotFoundException() {
        super("Project was not found");
    }
}
