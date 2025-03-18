package com.mayk.TaskComplete.core.validators;

import java.util.ArrayList;
import java.util.List;

public class NotificationError {
    private List<String> errors;

    public NotificationError() {
        errors = new ArrayList<>();
    }

    public void addError(String msg) {
        this.errors.add(msg);
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
