package com.mayk.TaskComplete.core.exception;

import java.util.List;

public class TaskUpdateStatusException extends RuntimeException{

    public TaskUpdateStatusException(List<String> errors) {
        super(String.join("\n", errors));
    }

}
