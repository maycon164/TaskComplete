package com.mayk.TaskComplete.core.exception;

import java.util.List;

public class AddNewTaskException extends RuntimeException {

    public AddNewTaskException(List<String> errors) {
        super(String.join("\n", errors));
    }
}
